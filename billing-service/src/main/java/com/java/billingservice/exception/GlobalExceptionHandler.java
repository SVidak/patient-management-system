package com.java.billingservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BillingAccountAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleBillingAccountAlreadyExistsException(BillingAccountAlreadyExistsException ex){

        log.warn("BillingAccount already exists {}", ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message", "BillingAccount already exists");

        return ResponseEntity.badRequest().body(errors);
    }
}
