package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.model.dto.queryObject.ResultQueryRecord;
import com.codecool.samu.codecoolinterview.service.target.StatisticsService;
import jakarta.persistence.Tuple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/average-result/student/email/{email}")
    public List<ResultQueryRecord> calculateAverageResultsByStudent(@PathVariable String email) {
        return statisticsService.calculateAverageResultsByStudent(email);
    }

    @GetMapping("/mentor-pass-rates")
    public List<Map<Tuple, Double>> calculateMentorPassRatePerAttempt() {
        return statisticsService.calculateMentorPassRatePerAttempt();
    }
}
