package com.codecool.samu.codecoolinterview.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("Email is already registered: %s".formatted(email));
    }
}
