package com.senla.web.exception;

public class MyAccessDeniedException extends RuntimeException {

    public MyAccessDeniedException(String message) {
        super(message);
    }
}
