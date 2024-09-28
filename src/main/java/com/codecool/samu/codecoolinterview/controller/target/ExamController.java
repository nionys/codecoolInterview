package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.service.target.ExamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/target/exam")
public class ExamController {
    private ExamService examService;
}
