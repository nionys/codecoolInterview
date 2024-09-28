package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.repository.StudentRepository;
import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NoSuchStudentException(id));
    }

    public long addStudent(Student student) {
        Student newStudent = studentRepository.save(student);
        return newStudent.getId();
    }
}
