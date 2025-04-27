package com.java.treatmentservice.exception;

public class TreatmentNotFoundException extends RuntimeException {
    public TreatmentNotFoundException(String message) {
        super(message);
    }
}
