package com.codecool.samu.codecoolinterview.service.source;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import com.codecool.samu.codecoolinterview.dbSource.repository.JsonRecordRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ExamDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SourceService {
    private JsonRecordRepository jsonRecordRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public SourceService(JsonRecordRepository jsonRecordRepository) {
        this.jsonRecordRepository = jsonRecordRepository;
    }

    public List<JsonRecord> getAllRecords() {
        return jsonRecordRepository.findAllByOrderByTimeAsc();
    }

    public List<JsonRecord> getAllRecordsWithSkip(int skip) {
        if (skip == 0) {
            return jsonRecordRepository.findAllByOrderByTimeAsc();
        } else {
            return jsonRecordRepository.findAllByOrderByTimeAsc(ScrollPosition.offset(skip-1));
        }
    }

    public long addRecord(JsonRecord record) {
        JsonRecord newRecord = jsonRecordRepository.save(record);
        return newRecord.getId();
    }

    public JsonRecord getRecordById(long id) {
        JsonRecord record = jsonRecordRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Record not found"));
        ExamDto examDto;
        try {
            examDto = objectMapper.readValue(record.getJson(), ExamDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return record;

    }
}
