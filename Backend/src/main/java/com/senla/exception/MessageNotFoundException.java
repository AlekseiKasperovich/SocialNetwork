package com.senla.exception;

/** @author Aliaksei Kaspiarovich */
public class MessageNotFoundException extends EntityNotFoundException {

    public MessageNotFoundException(String message) {
        super(message);
    }
}
