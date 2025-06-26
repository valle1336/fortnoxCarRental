package com.example.rental.exceptions;

public class RentalValidationException extends RuntimeException {
    public RentalValidationException(String message) {
        super(message);
    }
}

