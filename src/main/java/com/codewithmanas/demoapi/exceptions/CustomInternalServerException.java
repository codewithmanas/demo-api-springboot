package com.codewithmanas.demoapi.exceptions;

public class CustomInternalServerException extends RuntimeException {
    public CustomInternalServerException(String message) {
        super(message);
    }
}