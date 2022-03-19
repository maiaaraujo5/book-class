package com.maiaaraujo5.bookclass.controller.exception;

import com.maiaaraujo5.bookclass.exception.AppointmentAlreadyExists;
import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import com.maiaaraujo5.bookclass.exception.TeacherNotFound;
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

    @ExceptionHandler(TeacherNotFound.class)
    public ResponseEntity<ResponseException> handleTeacherNotFound(TeacherNotFound teacherNotFound) {
        ResponseException responseException = new ResponseException(HttpStatus.NOT_FOUND.name(), teacherNotFound.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppointmentAlreadyExists.class)
    public ResponseEntity<ResponseException> handleAppointmentAlreadyExists(AppointmentAlreadyExists appointmentAlreadyExists) {
        ResponseException responseException = new ResponseException(HttpStatus.CONFLICT.name(), appointmentAlreadyExists.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseException> handleInvalidTokenException(InvalidTokenException invalidTokenException) {
        ResponseException responseException = new ResponseException(HttpStatus.FORBIDDEN.name(), invalidTokenException.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.FORBIDDEN);
    }
}
