package com.stackoverflow.soq76090017.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.stackoverflow.soq76090017.ApplicationException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = { ApplicationException.class })
    public ResponseEntity<ApplicationException> handleApplicationException(ApplicationException applicationException) {
        return new ResponseEntity<>(applicationException, HttpStatus.valueOf(applicationException.getHttpStatusCode()));
    }
}
