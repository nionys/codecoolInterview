package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.repository.PersonRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.StudentRepository;
import com.codecool.samu.codecoolinterview.exception.NoSuchPersonException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private PersonRepository personRepository;

    public StudentService(StudentRepository studentRepository, PersonRepository personRepository) {
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public long promoteToStudent(long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new NoSuchPersonException(personId));
        Student student = studentRepository.save(new Student(person));
        return student.getId();
    }


}
