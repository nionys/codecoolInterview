package com.codecool.samu.codecoolinterview.repository.target.statistics;

import com.codecool.samu.codecoolinterview.model.dto.queryObject.MentorQueryRecord;
import com.codecool.samu.codecoolinterview.model.entity.target.Mentor;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Map;

public interface MentorStatisticsRepository extends Repository<Mentor, Long> {
    List<MentorQueryRecord> calculateMentorPassRatePerAttempt();
}
