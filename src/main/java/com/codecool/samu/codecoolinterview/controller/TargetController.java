package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.service.TargetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {

    private TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping("/ping")
    public String ping() {
        return targetService.ping();
    }

    @PostMapping("/student")
    public long addStudent(@RequestBody Student student) {
        return targetService.addStudent(student);
    }

    @GetMapping("/student/all")
    public List<Student> getAllStudents() {
        return targetService.getAllStudents();
    }

    @GetMapping("/student/{name}")
    public Student getStudent(@PathVariable String name) {
        return targetService.getStudent(name);
    }
}
