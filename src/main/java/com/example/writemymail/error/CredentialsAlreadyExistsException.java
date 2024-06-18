package com.example.writemymail.error;

public class CredentialsAlreadyExistsException extends RuntimeException {
    public CredentialsAlreadyExistsException(String message) {
        super(message);
    }
}