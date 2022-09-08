package com.senla.web.exception;

public class MyServerErrorException extends RuntimeException {

    public MyServerErrorException(String message) {
        super(message);
    }
}
