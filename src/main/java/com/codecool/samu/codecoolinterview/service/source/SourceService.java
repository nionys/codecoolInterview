package com.codecool.samu.codecoolinterview.service.source;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import com.codecool.samu.codecoolinterview.dbSource.repository.JsonRecordRepository;
import com.codecool.samu.codecoolinterview.jacksonObject.Exam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        return jsonRecordRepository.findAll();
    }

    public long addRecord(JsonRecord record) {
        JsonRecord newRecord = jsonRecordRepository.save(record);
        return newRecord.getId();
    }

    public JsonRecord getRecordById(long id) {
        JsonRecord record = jsonRecordRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Record not found"));
        Exam exam;
        try {
            exam = objectMapper.readValue(record.getJson(), Exam.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return record;

    }
}
