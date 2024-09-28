package com.codecool.samu.codecoolinterview.exception;

public class NoSuchPersonException extends RuntimeException {
    public NoSuchPersonException(long id) {
        super("No user with id: %d".formatted(id));
    }
    public NoSuchPersonException(String name) {
        super("No user with name: %s".formatted(name));
    }
}
