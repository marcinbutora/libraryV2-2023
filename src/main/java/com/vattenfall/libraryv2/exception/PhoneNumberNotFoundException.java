package com.vattenfall.libraryv2.exception;

import static java.time.LocalDateTime.now;

public class PhoneNumberNotFoundException extends RuntimeException {
    public PhoneNumberNotFoundException(String message) {
        super(message + " at " + now());
    }
}
