package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.service.CopyService;
import com.codecool.samu.codecoolinterview.service.target.ExamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/copy")
public class CopyController {
    private CopyService copyService;

    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @PostMapping("")
    public void copy() {
        copyService.copy();
    }
}
