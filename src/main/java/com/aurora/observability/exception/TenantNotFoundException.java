package com.aurora.observability.exception;

public class TenantNotFoundException extends IllegalStateException {
    public TenantNotFoundException (String message) {
        super(message);
    }
}
