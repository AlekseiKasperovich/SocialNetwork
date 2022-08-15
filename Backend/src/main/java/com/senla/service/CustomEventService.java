package com.senla.service;

import com.senla.model.Event;
import com.senla.model.User;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface CustomEventService {

    Event findEventById(UUID id);

    void checkUserOnEvent(User user, Event event);
}
