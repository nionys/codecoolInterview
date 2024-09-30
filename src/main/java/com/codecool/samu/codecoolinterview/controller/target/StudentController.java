package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.model.dto.NewStudentDto;
import com.codecool.samu.codecoolinterview.model.dto.PersonDto;
import com.codecool.samu.codecoolinterview.model.dto.StudentDto;
import com.codecool.samu.codecoolinterview.service.target.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/promote")
    public long promoteToStudent(@RequestBody String email) {
        return studentService.promoteToStudent(email);
    }

    @GetMapping("/all")
    public List<StudentDto> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/id/{id}")
    public StudentDto getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("")
    public NewStudentDto createStudent(@RequestBody PersonDto person) {
        return studentService.createStudent(person);
    }
}
