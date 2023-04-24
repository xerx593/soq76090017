package com.stackoverflow.soq76090017;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(-1, message, HttpStatus.NOT_FOUND);
    }

}
