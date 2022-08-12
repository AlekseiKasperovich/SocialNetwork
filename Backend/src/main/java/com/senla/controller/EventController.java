package com.senla.controller;

import com.senla.dto.event.CreateEventDto;
import com.senla.dto.event.EventDto;
import com.senla.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/events",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * @param eventId event ID
     * @param id id
     * @return event
     */
    @GetMapping("{eventId}")
    public EventDto getEventById(
            @PathVariable Long eventId, @RequestHeader("${request.id}") Long id) {
        return eventService.getEventById(eventId, id);
    }

    /**
     * @param createEventDto event name and description
     * @param id id
     * @return event
     */
    @PostMapping
    public EventDto createEvent(
            @RequestBody CreateEventDto createEventDto, @RequestHeader("${request.id}") Long id) {
        return eventService.createEvent(createEventDto, id);
    }

    /**
     * @param eventId event ID
     * @param createEventDto event name and description
     * @param id id
     * @return updated event
     */
    @PutMapping("{eventId}")
    public EventDto updateEvent(
            @PathVariable Long eventId,
            @RequestBody CreateEventDto createEventDto,
            @RequestHeader("${request.id}") Long id) {
        return eventService.updateEvent(eventId, createEventDto, id);
    }

    /**
     * @param eventId event ID
     * @param id id
     */
    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable Long eventId, @RequestHeader("${request.id}") Long id) {
        eventService.deleteEvent(eventId, id);
    }

    /**
     * @param eventId event ID
     * @param userId user ID
     * @param id id
     * @return event
     */
    @PutMapping("{eventId}/users/{userId}")
    public EventDto addUserToEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            @RequestHeader("${request.id}") Long id) {
        return eventService.addUser(eventId, userId, id);
    }

    /**
     * @param eventId event ID
     * @param userId user ID
     * @param id id
     * @return event
     */
    @DeleteMapping("{eventId}/users/{userId}")
    public EventDto deleteUserFromEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            @RequestHeader("${request.id}") Long id) {
        return eventService.deleteUser(eventId, userId, id);
    }

    /**
     * @param id id
     * @param pageable pagination information
     * @return events
     */
    @GetMapping
    public Page<EventDto> findMyEvents(@RequestHeader("${request.id}") Long id, Pageable pageable) {
        return eventService.findMyEvents(id, pageable);
    }
}
