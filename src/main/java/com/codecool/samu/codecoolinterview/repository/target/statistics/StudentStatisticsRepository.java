package com.codecool.samu.codecoolinterview.repository.target.statistics;

import com.codecool.samu.codecoolinterview.model.dto.queryObject.ResultQueryRecord;

import java.util.List;

public interface StudentStatisticsRepository {
    List<ResultQueryRecord> calculateAverageResultsByStudent(String studentEmail);
}
