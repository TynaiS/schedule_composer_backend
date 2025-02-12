package com.example.schedule_composer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoFieldsProvidedException extends RuntimeException {
    public NoFieldsProvidedException(String message) {
        super(message);
    }
}

