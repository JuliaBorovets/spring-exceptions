package com.softserve.itacademy.exception;

public class NullEntityReferenceException extends Exception {
    public NullEntityReferenceException(){}

    public NullEntityReferenceException(String message) {
        super(message);
    }
}
