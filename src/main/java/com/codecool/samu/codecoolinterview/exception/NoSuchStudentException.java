package com.codecool.samu.codecoolinterview.exception;

public class NoSuchStudentException extends RuntimeException {
    public NoSuchStudentException(long id) {
        super("NO student with id %d".formatted(id));
    }
}
