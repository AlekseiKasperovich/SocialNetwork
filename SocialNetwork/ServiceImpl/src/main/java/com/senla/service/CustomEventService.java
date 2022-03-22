package com.senla.service;

import com.senla.model.Event;
import com.senla.model.User;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CustomEventService {

    Event findEventById(Long id);

    void checkUserOnEvent(User user, Event event);
}
