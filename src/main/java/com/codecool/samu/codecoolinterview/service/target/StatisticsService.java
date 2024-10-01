package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.model.dto.queryObject.MentorQueryRecord;
import com.codecool.samu.codecoolinterview.repository.target.statistics.MentorStatisticsRepository;
import com.codecool.samu.codecoolinterview.repository.target.statistics.StatisticsRepository;
import com.codecool.samu.codecoolinterview.repository.target.statistics.StudentStatisticsRepository;
import com.codecool.samu.codecoolinterview.model.dto.queryObject.ResultQueryRecord;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<ResultQueryRecord> calculateAverageResultsByStudent(String studentEmail) {
        return statisticsRepository.calculateAverageResultsByStudent(studentEmail);
    }

    public List<MentorQueryRecord> calculateMentorPassRatePerAttempt() {
        return statisticsRepository.calculateMentorPassRatePerAttempt();
    }
}
