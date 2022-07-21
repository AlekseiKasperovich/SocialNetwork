package com.senla.api.exception;

/**
 * @author Aliaksei Kaspiarovich
 */
public class CommunityNotFoundException extends EntityNotFoundException {

    public CommunityNotFoundException(String message) {
        super(message);
    }
}
