package com.senla.api.exception;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
