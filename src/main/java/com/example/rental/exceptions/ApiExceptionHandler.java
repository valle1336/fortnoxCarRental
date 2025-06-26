package com.example.rental.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RentalValidationException.class)
    public ResponseEntity<String> handleRentalValidation(RentalValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
