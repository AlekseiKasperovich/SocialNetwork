package com.senla.client;

import com.senla.dto.event.CreateEventDto;
import com.senla.dto.event.EventDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface EventRestClient {

    EventDto getEventById(UUID eventId);

    EventDto createEvent(CreateEventDto createEventDto);

    EventDto updateEvent(UUID eventId, CreateEventDto createEventDto);

    void deleteEvent(UUID eventId);

    EventDto addUser(UUID eventId, UUID userId);

    EventDto deleteUser(UUID eventId, UUID userId);

    Page<EventDto> findMyEvents(Pageable pageable, HttpServletRequest request);
}
