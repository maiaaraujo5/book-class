package com.maiaaraujo5.bookclass.controller.exception;

import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TeacherAlreadyExists.class)
    public ResponseEntity<ResponseException> handleTeacherAlreadyExists(TeacherAlreadyExists teacherAlreadyExists) {
        ResponseException responseException = new ResponseException(HttpStatus.CONFLICT.name(), teacherAlreadyExists.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.CONFLICT);
    }
}
