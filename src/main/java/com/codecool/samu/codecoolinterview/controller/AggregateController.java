package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.dbTarget.model.*;
import com.codecool.samu.codecoolinterview.dto.QueryResult;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import com.codecool.samu.codecoolinterview.service.target.StudentAverageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregate")
public class AggregateController {
    @Autowired
    EntityManager entityManager;
    StudentAverageService studentAverageService;
    public AggregateController(StudentAverageService studentAverageService) {
        this.studentAverageService = studentAverageService;
    }

    @GetMapping("/average/student/email/{email}")
    public List<QueryResult> calculateAverageResults(@PathVariable String email) {
        return studentAverageService.averageOfStudentPerDimension(email);
    }
}
