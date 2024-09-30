package com.codecool.samu.codecoolinterview.exception;

public class NoSuchMentorException extends NoSuchPersonException{
    public NoSuchMentorException(String email) {
        super("mentor", email);
    }
    public NoSuchMentorException(long id) {
        super("mentor", id);
    }
}
