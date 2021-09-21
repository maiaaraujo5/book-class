package com.maiaaraujo5.bookclass.exception;

import lombok.Getter;

@Getter
public class TeacherAlreadyExists extends RuntimeException {
    private final String message;

    public TeacherAlreadyExists(String message) {
        this.message = message;
    }
}
