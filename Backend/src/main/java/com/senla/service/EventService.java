package com.senla.service;

import com.senla.dto.event.CreateEventDto;
import com.senla.dto.event.EventDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface EventService {

    EventDto getEventById(UUID eventId, UUID id);

    EventDto createEvent(CreateEventDto createEventDto, UUID id);

    EventDto updateEvent(UUID eventId, CreateEventDto createEventDto, UUID id);

    void deleteEvent(UUID eventId, UUID id);

    EventDto addUser(UUID eventId, UUID userId, UUID id);

    EventDto deleteUser(UUID eventId, UUID userId, UUID id);

    Page<EventDto> findMyEvents(UUID id, Pageable pageable);
}
