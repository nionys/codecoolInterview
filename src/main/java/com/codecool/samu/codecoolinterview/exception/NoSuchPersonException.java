package com.codecool.samu.codecoolinterview.exception;

public class NoSuchPersonException extends RuntimeException {
    protected NoSuchPersonException(String userType, long id) {
        super("No %s with id: %d".formatted(userType, id));
    }
    protected NoSuchPersonException(String userType, String email) {
        super("No %s with email: %s".formatted(userType, email));
    }

    public NoSuchPersonException(String email) {
        this("user", email);
    }
    public NoSuchPersonException(long id) {
        this("user", id);
    }
}
