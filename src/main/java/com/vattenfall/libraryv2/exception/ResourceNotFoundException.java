package com.vattenfall.libraryv2.exception;

import static java.time.LocalDateTime.now;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message + " at " + now());
    }
}
