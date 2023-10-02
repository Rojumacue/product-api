package com.product.productapi.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestTemplateCustomError.class)
    public ResponseEntity<String> handleCustomApiException(RestTemplateCustomError ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }
}
