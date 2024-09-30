package com.codecool.samu.codecoolinterview.repository.source;

import com.codecool.samu.codecoolinterview.model.entity.source.JsonRecord;
import org.springframework.data.domain.OffsetScrollPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JsonRecordRepository extends JpaRepository<JsonRecord, Long> {
    List<JsonRecord> findAllByOrderByTimeAsc();
    List<JsonRecord> findAllByOrderByTimeAsc(OffsetScrollPosition offset);
}
