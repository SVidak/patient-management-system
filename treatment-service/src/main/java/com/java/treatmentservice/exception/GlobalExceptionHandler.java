package com.java.treatmentservice.exception;

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

    @ExceptionHandler(TreatmentNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNoTreatmentFoundException(TreatmentNotFoundException ex) {

        log.warn("Treatment not exists {}", ex.getMessage());

        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Treatment not found");

        return ResponseEntity.badRequest().body(errors);
    }
}
