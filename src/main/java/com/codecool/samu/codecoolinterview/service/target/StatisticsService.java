package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.repository.statistics.MentorStatisticsRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.statistics.StudentStatisticsRepository;
import com.codecool.samu.codecoolinterview.dto.queryObject.MentorQueryObject;
import com.codecool.samu.codecoolinterview.dto.queryObject.ResultQueryObject;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private final StudentStatisticsRepository studentStatisticsRepository;
    private final MentorStatisticsRepository mentorStatisticsRepository;
    public StatisticsService(
        StudentStatisticsRepository studentStatisticsRepository,
        MentorStatisticsRepository mentorStatisticsRepository
    ) {
        this.studentStatisticsRepository = studentStatisticsRepository;
        this.mentorStatisticsRepository = mentorStatisticsRepository;
    }

    public List<ResultQueryObject> calculateAverageResultsByStudent(String studentEmail) {
        return studentStatisticsRepository.calculateAverageResultsByStudent(studentEmail);
    }

    public List<Map<Tuple, Double>> calculateMentorPassRatePerAttempt() {
        return mentorStatisticsRepository.calculateMentorPassRatePerAttempt();
    }
}
