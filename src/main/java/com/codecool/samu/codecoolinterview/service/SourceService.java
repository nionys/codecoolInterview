package com.codecool.samu.codecoolinterview.service;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import com.codecool.samu.codecoolinterview.dbSource.repository.JsonRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {
    private JsonRecordRepository jsonRecordRepository;

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
}
