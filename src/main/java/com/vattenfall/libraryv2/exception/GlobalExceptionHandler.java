package com.vattenfall.libraryv2.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PhoneNumberNotFoundException.class)
    public ErrorResponse handlePhoneNumberNotFound(PhoneNumberNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }
}
