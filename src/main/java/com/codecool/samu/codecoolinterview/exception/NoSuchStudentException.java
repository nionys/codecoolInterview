package com.codecool.samu.codecoolinterview.exception;

public class NoSuchStudentException extends RuntimeException {
    public NoSuchStudentException(long id) {
        super("No student with id %d".formatted(id));
    }
    public NoSuchStudentException(String email) {
        super("No student with email %s".formatted(email));
    }
}
