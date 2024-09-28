package com.codecool.samu.codecoolinterview.exception;

public class PersonIsAlreadyAStudentException extends RuntimeException {
    public PersonIsAlreadyAStudentException(String email) {
        super("Person with email %s is already a student".formatted(email));
    }
}
