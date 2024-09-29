package com.codecool.samu.codecoolinterview.dto;

import com.codecool.samu.codecoolinterview.dto.jacksonObject.ExamDto;

public record CopyResult(boolean success, String message, ExamDto object) {


}
