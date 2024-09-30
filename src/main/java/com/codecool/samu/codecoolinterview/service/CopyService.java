package com.codecool.samu.codecoolinterview.service;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import com.codecool.samu.codecoolinterview.dto.CopyResult;
import com.codecool.samu.codecoolinterview.dto.ExamDto;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ParsedExamDto;
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

    private final ObjectMapper objectMapper = new ObjectMapper();
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
            ParsedExamDto exam;
            ExamDto newExamDto;
            try {
                exam = objectMapper.readValue(record.getJson(), ParsedExamDto.class);
                newExamDto = examService.saveExam(exam);
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
