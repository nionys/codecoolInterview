package com.codecool.samu.codecoolinterview.service.source;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import com.codecool.samu.codecoolinterview.dbSource.repository.JsonRecordRepository;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SourceService {
    private final JsonRecordRepository jsonRecordRepository;

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

    public JsonRecord addRecord(JsonRecord record) {
        return jsonRecordRepository.save(record);
    }

    public JsonRecord getRecordById(long id) {
        return jsonRecordRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Record not found"));

    }
}
