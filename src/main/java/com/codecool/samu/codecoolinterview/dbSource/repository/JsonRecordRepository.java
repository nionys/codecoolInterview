package com.codecool.samu.codecoolinterview.dbSource.repository;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonRecordRepository extends JpaRepository<JsonRecord, Long> {
}
