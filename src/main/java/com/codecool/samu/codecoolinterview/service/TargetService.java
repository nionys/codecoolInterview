package com.codecool.samu.codecoolinterview.service;

import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class TargetService {
    private StudentRepository studentRepository;

    public TargetService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String ping() {
        return "pong";
    }

    public long addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent.getId();
    }

    public Student getStudent(String name) {
        return studentRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("No student with name \"%s\"".formatted(name)));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
