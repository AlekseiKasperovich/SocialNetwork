package com.senla.api.exception;

/**
 * @author Aliaksei Kaspiarovich
 */
public class CommunityMessageNotFoundException extends EntityNotFoundException {

    public CommunityMessageNotFoundException(String message) {
        super(message);
    }
}
