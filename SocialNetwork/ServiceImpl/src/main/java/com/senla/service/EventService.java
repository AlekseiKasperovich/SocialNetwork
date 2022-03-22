package com.senla.service;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface EventService {

    EventDto getEventById(Long eventId, String email);

    EventDto createEvent(CreateEventDto createEventDto, String email);

    EventDto updateEvent(Long eventId, CreateEventDto createEventDto, String email);

    void deleteEvent(Long eventId, String email);

    EventDto addUser(Long eventId, Long userId, String email);

    EventDto deleteUser(Long eventId, Long userId, String email);

    Page<EventDto> findMyEvents(String email, Pageable pageable);

}
