package com.senla.api.exception;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public class EventNotFoundException extends EntityNotFoundException {

    public EventNotFoundException(String message) {
        super(message);
    }
}
