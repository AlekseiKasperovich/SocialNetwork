package com.senla.service;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface EventService {

    EventDto getEventById(Long eventId, Long id);

    EventDto createEvent(CreateEventDto createEventDto, Long id);

    EventDto updateEvent(Long eventId, CreateEventDto createEventDto, Long id);

    void deleteEvent(Long eventId, Long id);

    EventDto addUser(Long eventId, Long userId, Long id);

    EventDto deleteUser(Long eventId, Long userId, Long id);

    Page<EventDto> findMyEvents(Long id, Pageable pageable);

}
