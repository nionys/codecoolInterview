package com.codecool.samu.codecoolinterview.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(long id) {
        super("No user with id: %d".formatted(id));
    }
    public NoSuchUserException(String name) {
        super("No user with name: %s".formatted(name));
    }
}
