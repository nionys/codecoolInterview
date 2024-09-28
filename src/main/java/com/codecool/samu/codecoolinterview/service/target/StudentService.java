package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.repository.PersonRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.StudentRepository;
import com.codecool.samu.codecoolinterview.dto.PersonDto;
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

    public long promoteToStudent(String email) {
        Person person = personService.findByEmail(email);
        return promoteToStudent(person);
    }

    public long createStudent(Person person) {
        long personId = personService.addUser(person);
        return promoteToStudent(person);
    }

    private long promoteToStudent(Person person) {
        Student student = studentRepository.save(new Student(person));
        return student.getId();
    }


}
