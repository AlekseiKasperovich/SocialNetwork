package com.senla.impl.service.custom;

import com.senla.api.exception.EventNotFoundException;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.impl.model.Event;
import com.senla.impl.model.User;
import com.senla.impl.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomEventService {

    private final EventRepository eventRepository;

    /**
     *
     * @param id event ID
     * @return event
     */
    public Event findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(()
                        -> new EventNotFoundException(
                        String.format("Event with id = %s is not found", id)));
    }

    /**
     *
     * @param user user
     * @param event event
     */
    public void checkUserOnEvent(User user, Event event) {
        if (!event.getParticipants().contains(user)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

}
