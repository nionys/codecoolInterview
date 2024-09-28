package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dto.PersonDto;
import com.codecool.samu.codecoolinterview.service.target.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/student")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/promote")
    public long promoteToStudent(@RequestBody String email) {
        return studentService.promoteToStudent(email);
    }

    @GetMapping("/all")
    public List<Person> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("")
    public long createStudent(@RequestBody Person person) {
        return studentService.createStudent(person);
    }
}
