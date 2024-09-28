package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.repository.PersonRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.StudentRepository;
import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import com.codecool.samu.codecoolinterview.exception.PersonIsAlreadyAStudentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private PersonService personService;
    private StudentRepository studentRepository;
    private PersonRepository personRepository;

    public StudentService(PersonService personService, StudentRepository studentRepository, PersonRepository personRepository) {
        this.personService = personService;
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
    }

    public List<Person> getAllStudents() {
       return studentRepository.findAll().stream()
           .map(Student::getPerson)
           .toList();
    }

    public Person getStudentById(long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new NoSuchStudentException(id))
            .getPerson();
    }

    public long promoteToStudent(String email) {
        Person person = personService.findByEmail(email);
        return promoteToStudent(person);
    }

    public long createStudent(Person person) {
        long personId = personService.addUser(person);
        return promoteToStudent(person);
    }

    private long promoteToStudent(Person person) {
        Student student;
        try {
            student = studentRepository.save(new Student(person));
        } catch (DataIntegrityViolationException e) {
            throw new PersonIsAlreadyAStudentException(person.getEmail());
        }
        return student.getId();
    }
}
