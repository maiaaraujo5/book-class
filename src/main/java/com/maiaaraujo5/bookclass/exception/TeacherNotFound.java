package com.maiaaraujo5.bookclass.exception;

import lombok.Getter;

@Getter
public class TeacherNotFound extends Exception {

    public TeacherNotFound(String message) {
        super(message);
    }
}
