package com.codecool.samu.codecoolinterview.exception;

public class PersonIsAlreadyAStudentException extends RuntimeException {
    public PersonIsAlreadyAStudentException(String email) {
        super("Person with email is already a student: %s".formatted(email));
    }
}
