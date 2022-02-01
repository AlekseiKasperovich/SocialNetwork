package com.senla.rest.controller;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.api.service.EventService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     *
     * @param eventId event ID
     * @return event
     */
    @GetMapping("{eventId}")
    public EventDto getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }

    /**
     *
     * @param createEventDto event name and description
     * @return event
     */
    @PostMapping
    public EventDto createEvent(@Valid @RequestBody CreateEventDto createEventDto) {
        return eventService.createEvent(createEventDto);
    }

    /**
     *
     * @param eventId event ID
     * @param createEventDto event name and description
     * @return updated event
     */
    @PutMapping("{eventId}")
    public EventDto updateEvent(@PathVariable Long eventId,
            @Valid @RequestBody CreateEventDto createEventDto) {
        return eventService.updateEvent(eventId, createEventDto);
    }

    /**
     *
     * @param eventId event ID
     */
    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }

    /**
     *
     * @param eventId event ID
     * @param userId user ID
     * @return event
     */
    @PutMapping("{eventId}/users/{userId}")
    public EventDto addUserToEvent(@PathVariable Long eventId,
            @PathVariable Long userId) {
        return eventService.addUser(eventId, userId);
    }

    /**
     *
     * @param eventId event ID
     * @param userId user ID
     * @return event
     */
    @DeleteMapping("{eventId}/users/{userId}")
    public EventDto deleteUserFromEvent(@PathVariable Long eventId,
            @PathVariable Long userId) {
        return eventService.deleteUser(eventId, userId);
    }

    /**
     *
     * @param pageable pagination information
     * @return events
     */
    @GetMapping
    public Page<EventDto> findMyEvents(Pageable pageable) {
        return eventService.findMyEvents(pageable);
    }
}
