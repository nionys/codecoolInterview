package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.service.target.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {
    @RestController
    @RequestMapping("/student")
    public static class StudentController {
        private StudentService studentService;
        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping("/all")
        public List<Student> getAllStudents() {
            return studentService.findAllStudents();
        }

        @PostMapping("/")
        public long addStudent(@RequestBody Student student) {
            return studentService.addStudent(student);
        }

        @GetMapping("/{id}")
        public Student getStudentById(@PathVariable long id) {
            return studentService.findStudentById(id);
        }
    }
}
