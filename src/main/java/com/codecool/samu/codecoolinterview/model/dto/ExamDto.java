package com.codecool.samu.codecoolinterview.model.dto;

import com.codecool.samu.codecoolinterview.model.dto.jacksonObject.ResultDto;

import java.util.Date;
import java.util.List;

public record ExamDto(
    String module,
    MentorDto mentor,
    StudentDto student,
    Date date,
    boolean cancelled,
    String comment,
    Boolean success,
    List<ResultDto> resultDtos) {

}
