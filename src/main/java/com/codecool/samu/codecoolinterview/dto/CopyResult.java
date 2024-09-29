package com.codecool.samu.codecoolinterview.dto;

import com.codecool.samu.codecoolinterview.dto.jacksonObject.ParsedExamDto;

public record CopyResult(boolean success, String message, ExamDto object) {


}
