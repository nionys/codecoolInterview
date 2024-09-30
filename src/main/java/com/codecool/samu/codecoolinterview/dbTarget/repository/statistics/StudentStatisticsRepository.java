package com.codecool.samu.codecoolinterview.dbTarget.repository.statistics;

import com.codecool.samu.codecoolinterview.dto.queryObject.ResultQueryObject;

import java.util.List;

public interface StudentStatisticsRepository {
    List<ResultQueryObject> calculateAverageResultsByStudent(String studentEmail);
}
