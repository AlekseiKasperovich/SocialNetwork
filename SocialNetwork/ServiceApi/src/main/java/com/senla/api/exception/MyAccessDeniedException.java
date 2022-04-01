package com.senla.api.exception;

/**
 * @author Aliaksei Kaspiarovich
 */
public class MyAccessDeniedException extends RuntimeException {

    public MyAccessDeniedException(String message) {
        super(message);
    }
}
