package com.codecool.samu.codecoolinterview.exception;

public class NoSuchStudentException extends NoSuchPersonException {
    public NoSuchStudentException(String email) {
        super("student", email);
    }
    public NoSuchStudentException(long id) {
        super("student", id);
    }
}
