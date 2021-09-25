package com.maiaaraujo5.bookclass.exception;

import lombok.Getter;


public class TeacherAlreadyExists extends Exception {

    public TeacherAlreadyExists(String message) {
        super(message);
    }
}
