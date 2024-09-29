package com.codecool.samu.codecoolinterview.service;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import com.codecool.samu.codecoolinterview.dbTarget.repository.ExamRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ExamDto;
import com.codecool.samu.codecoolinterview.exception.SourceJsonFormatException;
import com.codecool.samu.codecoolinterview.service.source.SourceService;
import com.codecool.samu.codecoolinterview.service.target.ExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {
    private int skip = 0;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ExamService examService;
    private SourceService sourceService;
    public CopyService(ExamService examService, SourceService sourceService) {
        this.examService = examService;
        this.sourceService = sourceService;
    }

    public void copy() {
        RuntimeException firstException = null;
        List<JsonRecord> unreadRecords = sourceService.getAllRecordsWithSkip(skip);
        skip += unreadRecords.size();
        for (JsonRecord record : unreadRecords) {
            ExamDto newExam;
            try {
                newExam = objectMapper.readValue(record.getJson(), ExamDto.class);
            } catch (JsonProcessingException e) {
                if (firstException == null) {
                    firstException = new SourceJsonFormatException(e.getMessage());
                }
                continue;
            }
            examService.saveExam(newExam);
        }
        if (firstException != null) {
            throw firstException;
        }
    }
}
