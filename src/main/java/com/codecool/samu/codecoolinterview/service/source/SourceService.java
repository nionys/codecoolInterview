package com.codecool.samu.codecoolinterview.service.source;

import com.codecool.samu.codecoolinterview.model.dto.NewJsonRecordDto;
import com.codecool.samu.codecoolinterview.model.entity.source.JsonRecord;
import com.codecool.samu.codecoolinterview.repository.source.JsonRecordRepository;
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

    public NewJsonRecordDto addRecord(String record) {
        JsonRecord newJsonRecord = jsonRecordRepository.save(new JsonRecord(record));
        return new NewJsonRecordDto(newJsonRecord.getId(), newJsonRecord.getTime());
    }

    public JsonRecord getRecordById(long id) {
        return jsonRecordRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Record not found"));

    }

    public void deleteAll() {
        jsonRecordRepository.deleteAll();
    }
}
