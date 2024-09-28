package com.codecool.samu.codecoolinterview.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("%s email is already registered".formatted(email));
    }
}
