package com.example.writemymail.error;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message){super(message);}
}
