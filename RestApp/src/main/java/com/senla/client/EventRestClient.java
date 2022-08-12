package com.senla.client;

import com.senla.dto.event.CreateEventDto;
import com.senla.dto.event.EventDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface EventRestClient {

    EventDto getEventById(Long eventId);

    EventDto createEvent(CreateEventDto createEventDto);

    EventDto updateEvent(Long eventId, CreateEventDto createEventDto);

    void deleteEvent(Long eventId);

    EventDto addUser(Long eventId, Long userId);

    EventDto deleteUser(Long eventId, Long userId);

    Page<EventDto> findMyEvents(Pageable pageable, HttpServletRequest request);
}
