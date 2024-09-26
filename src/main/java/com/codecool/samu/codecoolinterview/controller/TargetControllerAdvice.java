package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class TargetControllerAdvice {
    @ExceptionHandler(NoSuchStudentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoSuchStudent(NoSuchStudentException exception) {
        return exception.getMessage();
    }
}
