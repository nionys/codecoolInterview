package com.codecool.samu.codecoolinterview.service;

import com.codecool.samu.codecoolinterview.model.entity.source.JsonRecord;
import com.codecool.samu.codecoolinterview.model.dto.CopyResult;
import com.codecool.samu.codecoolinterview.model.dto.ExamDto;
import com.codecool.samu.codecoolinterview.model.dto.jacksonObject.ParsedExamDto;
import com.codecool.samu.codecoolinterview.exception.NoSuchPersonException;
import com.codecool.samu.codecoolinterview.service.source.SourceService;
import com.codecool.samu.codecoolinterview.service.target.ExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CopyService {
    private int skip = 0;

    private final ExamService examService;
    private final SourceService sourceService;
    public CopyService(ExamService examService, SourceService sourceService) {
        this.examService = examService;
        this.sourceService = sourceService;
    }

    public List<CopyResult> copy() {
        List<CopyResult> copyResults = new ArrayList<>();
        List<JsonRecord> unreadRecords = sourceService.getAllRecordsWithSkip(skip);
        for (JsonRecord record : unreadRecords) {
            ExamDto newExamDto;
            try {
                newExamDto = examService.saveExam(record.getJson());
            } catch (JsonProcessingException | NoSuchPersonException e) {
                copyResults.add(new CopyResult(false, e.getMessage(), null));
                skip++;
                continue;
            }
            skip++;
            copyResults.add(new CopyResult(true, "success", newExamDto));
        }
        return copyResults;
    }
}
