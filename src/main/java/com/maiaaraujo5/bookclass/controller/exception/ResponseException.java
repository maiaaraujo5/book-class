package com.maiaaraujo5.bookclass.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseException {
    private final String status;
    private final String message;
}
