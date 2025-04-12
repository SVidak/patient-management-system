package com.java.billingservice.exception;

public class BillingAccountAlreadyExistsException extends RuntimeException {
    public BillingAccountAlreadyExistsException(String message) {
        super(message);
    }
}
