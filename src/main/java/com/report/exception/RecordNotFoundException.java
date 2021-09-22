package com.report.exception;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
