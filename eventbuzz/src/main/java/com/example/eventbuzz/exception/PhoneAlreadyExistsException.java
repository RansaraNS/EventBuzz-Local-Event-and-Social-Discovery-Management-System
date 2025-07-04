package com.example.eventbuzz.exception;

public class PhoneAlreadyExistsException extends RuntimeException {
    public PhoneAlreadyExistsException(String message) {
        super(message);
    }
    
    public PhoneAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}