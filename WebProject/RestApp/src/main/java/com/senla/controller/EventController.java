package com.senla.controller;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.client.EventRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/events",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventRestClient eventRestClient;

    /**
     * @param eventId event ID
     * @return event
     */
    @GetMapping("{eventId}")
    public EventDto getEventById(@PathVariable Long eventId) {
        return eventRestClient.getEventById(eventId);
    }

    /**
     * @param createEventDto event name and description
     * @return event
     */
    @PostMapping
    public EventDto createEvent(@Valid @RequestBody CreateEventDto createEventDto) {
        return eventRestClient.createEvent(createEventDto);
    }

    /**
     * @param eventId        event ID
     * @param createEventDto event name and description
     * @return updated event
     */
    @PutMapping("{eventId}")
    public EventDto updateEvent(@PathVariable Long eventId,
                                @Valid @RequestBody CreateEventDto createEventDto) {
        return eventRestClient.updateEvent(eventId, createEventDto);
    }

    /**
     * @param eventId event ID
     */
    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventRestClient.deleteEvent(eventId);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @return event
     */
    @PutMapping("{eventId}/users/{userId}")
    public EventDto addUserToEvent(@PathVariable Long eventId,
                                   @PathVariable Long userId) {
        return eventRestClient.addUser(eventId, userId);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @return event
     */
    @DeleteMapping("{eventId}/users/{userId}")
    public EventDto deleteUserFromEvent(@PathVariable Long eventId,
                                        @PathVariable Long userId) {
        return eventRestClient.deleteUser(eventId, userId);
    }

    /**
     * @param pageable pagination information
     * @param request request
     * @return events
     */
    @GetMapping
    public Page<EventDto> findMyEvents(Pageable pageable, HttpServletRequest request) {
        return eventRestClient.findMyEvents(pageable, request);
    }
}
