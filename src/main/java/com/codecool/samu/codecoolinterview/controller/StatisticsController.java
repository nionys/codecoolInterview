package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import com.codecool.samu.codecoolinterview.dto.queryObject.MentorQueryObject;
import com.codecool.samu.codecoolinterview.dto.queryObject.ResultQueryObject;
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
    public List<ResultQueryObject> calculateAverageResultsByStudent(@PathVariable String email) {
        return statisticsService.calculateAverageResultsByStudent(email);
    }

    @GetMapping("/mentor-passrates")
    public List<Map<Tuple, Double>> calculateMentorPassRatePerAttempt() {
        return statisticsService.calculateMentorPassRatePerAttempt();
    }
}
