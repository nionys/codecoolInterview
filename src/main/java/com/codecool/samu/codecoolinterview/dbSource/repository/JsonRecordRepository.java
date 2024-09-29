package com.codecool.samu.codecoolinterview.dbSource.repository;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JsonRecordRepository extends JpaRepository<JsonRecord, Long> {
    public List<JsonRecord> findAllByOrderByTimeAsc();
    public List<JsonRecord> findAllByOrderByTimeAsc(OffsetScrollPosition offset);
}
