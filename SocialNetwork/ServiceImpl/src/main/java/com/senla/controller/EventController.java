package com.senla.controller;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/events",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * @param eventId event ID
     * @param email email
     * @return event
     */
    @GetMapping("{eventId}")
    public EventDto getEventById(@PathVariable Long eventId,
                                 @RequestHeader("${request.email}") String email) {
        return eventService.getEventById(eventId, email);
    }

    /**
     * @param createEventDto event name and description
     * @param email email
     * @return event
     */
    @PostMapping
    public EventDto createEvent(@RequestBody CreateEventDto createEventDto,
                                @RequestHeader("${request.email}") String email) {
        return eventService.createEvent(createEventDto, email);
    }

    /**
     * @param eventId        event ID
     * @param createEventDto event name and description
     * @param email email
     * @return updated event
     */
    @PutMapping("{eventId}")
    public EventDto updateEvent(@PathVariable Long eventId,
                                @RequestBody CreateEventDto createEventDto,
                                @RequestHeader("${request.email}") String email) {
        return eventService.updateEvent(eventId, createEventDto, email);
    }

    /**
     * @param eventId event ID
     * @param email email
     */
    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable Long eventId,
                            @RequestHeader("${request.email}") String email) {
        eventService.deleteEvent(eventId, email);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @param email email
     * @return event
     */
    @PutMapping("{eventId}/users/{userId}")
    public EventDto addUserToEvent(@PathVariable Long eventId,
                                   @PathVariable Long userId,
                                   @RequestHeader("${request.email}") String email) {
        return eventService.addUser(eventId, userId, email);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @param email email
     * @return event
     */
    @DeleteMapping("{eventId}/users/{userId}")
    public EventDto deleteUserFromEvent(@PathVariable Long eventId,
                                        @PathVariable Long userId,
                                        @RequestHeader("${request.email}") String email) {
        return eventService.deleteUser(eventId, userId, email);
    }

    /**
     * @param email email
     * @param pageable pagination information
     * @return events
     */
    @GetMapping
    public Page<EventDto> findMyEvents(
            @RequestHeader("${request.email}") String email, Pageable pageable) {
        return eventService.findMyEvents(email, pageable);
    }
}
