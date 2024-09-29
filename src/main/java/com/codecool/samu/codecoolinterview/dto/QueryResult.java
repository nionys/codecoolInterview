package com.codecool.samu.codecoolinterview.dto;

public record QueryResult(
    /* Everything is boxed to accommodate null values resulting from join */
    Long examId,
    Long heldExamId,
    String studentEmail,
    String mentorEmail,
    Boolean successful,
    Long resultId,
    String dimension,
    Integer result) {
}
