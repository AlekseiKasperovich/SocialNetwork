package com.senla.exception;

/** @author Aliaksei Kaspiarovich */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
