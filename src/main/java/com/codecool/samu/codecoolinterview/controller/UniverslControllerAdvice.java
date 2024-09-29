package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.exception.DuplicateEmailException;
import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import com.codecool.samu.codecoolinterview.exception.NoSuchPersonException;
import com.codecool.samu.codecoolinterview.exception.PersonIsAlreadyAStudentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UniverslControllerAdvice {
    @ExceptionHandler({
        NoSuchStudentException.class,
        NoSuchPersonException.class,
        DuplicateEmailException.class,
        PersonIsAlreadyAStudentException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoSuchStudent(RuntimeException exception) {
        return exception.getMessage();
    }
}
