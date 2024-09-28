package com.codecool.samu.codecoolinterview.exception;

public class NoSuchPersonException extends RuntimeException {
    public NoSuchPersonException(long id) {
        super("No user with id: %d".formatted(id));
    }
    public NoSuchPersonException(String email) {
        super("No user with email: %s".formatted(email));
    }
}
