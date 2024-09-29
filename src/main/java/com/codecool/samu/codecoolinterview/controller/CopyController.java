package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.dto.CopyResult;
import com.codecool.samu.codecoolinterview.service.CopyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/copy")
public class CopyController {
    private final CopyService copyService;

    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @PostMapping("")
    public List<CopyResult> copy() {
        return copyService.copy();
    }
}
