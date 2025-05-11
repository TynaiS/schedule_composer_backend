package com.example.schedule_composer.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException (String message) {
        super(message);
    }
}
