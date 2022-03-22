package com.senla.service.custom;

import com.senla.api.exception.EventNotFoundException;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.model.Event;
import com.senla.model.User;
import com.senla.repository.EventRepository;
import com.senla.service.CustomEventService;
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
public class CustomEventServiceImpl implements CustomEventService {

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
