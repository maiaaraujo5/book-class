package com.maiaaraujo5.bookclass.exception;

import lombok.Getter;

@Getter
public class Exception extends RuntimeException {

    private final String message;

    public Exception(String message) {
        this.message = message;
    }
}
