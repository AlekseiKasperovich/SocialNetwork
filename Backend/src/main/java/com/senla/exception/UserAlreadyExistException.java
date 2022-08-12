package com.senla.exception;

/** @author Aliaksei Kaspiarovich */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
