package com.senla.api.exception;

/**
 * @author Aliaksei Kaspiarovich
 */
public class EventMessageNotFoundException extends EntityNotFoundException {

    public EventMessageNotFoundException(String message) {
        super(message);
    }
}
