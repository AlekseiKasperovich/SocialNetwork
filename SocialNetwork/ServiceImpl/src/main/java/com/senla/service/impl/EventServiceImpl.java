package com.senla.service.impl;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.mapper.Mapper;
import com.senla.model.Event;
import com.senla.model.User;
import com.senla.repository.EventRepository;
import com.senla.service.CustomEventService;
import com.senla.service.CustomFriendshipService;
import com.senla.service.CustomUserService;
import com.senla.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final CustomFriendshipService friendshipService;
    private final CustomUserService userService;
    private final CustomEventService eventService;
    private final EventRepository eventRepository;
    private final Mapper mapper;

    /**
     * @param eventId event ID
     * @param email
     * @return event
     */
    @Override
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId, String email) {
        Event event = eventService.findEventById(eventId);
        eventService.checkUserOnEvent(userService.findUserByEmail(email), event);
        return mapper.map(event, EventDto.class);
    }

    /**
     * @param createEventDto event name and description
     * @param email
     * @return event
     */
    @Override
    public EventDto createEvent(CreateEventDto createEventDto, String email) {
        Event event = mapper.map(createEventDto, Event.class);
        User author = userService.findUserByEmail(email);
        event.setAuthor(author);
        event.getParticipants().add(author);
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    /**
     * @param eventId        event ID
     * @param createEventDto event name and description
     * @param email
     * @return updated event
     */
    @Override
    public EventDto updateEvent(Long eventId, CreateEventDto createEventDto, String email) {
        Event event = eventService.findEventById(eventId);
        checkEventAuthor(event, userService.findUserByEmail(email).getId());
        mapper.map(createEventDto, event);
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    /**
     * @param eventId event ID
     * @param email
     */
    @Override
    public void deleteEvent(Long eventId, String email) {
        Event event = eventService.findEventById(eventId);
        checkEventAuthor(event, userService.findUserByEmail(email).getId());
        eventRepository.deleteById(eventId);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @param email
     * @return event
     */
    @Override
    public EventDto addUser(Long eventId, Long userId, String email) {
        Event event = eventService.findEventById(eventId);
        User author = userService.findUserByEmail(email);
        checkEventAuthor(event, author.getId());
        User user = userService.findUserById(userId);
        if (friendshipService.checkFriendship(author.getId(), user.getId())) {
            event.getParticipants().add(user);
            return mapper.map(eventRepository.save(event), EventDto.class);
        }
        return mapper.map(event, EventDto.class);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @param email
     * @return event
     */
    @Override
    public EventDto deleteUser(Long eventId, Long userId, String email) {
        Event event = eventService.findEventById(eventId);
        User author = userService.findUserByEmail(email);
        checkEventAuthor(event, author.getId());
        User user = userService.findUserById(userId);
        if (!author.getId().equals(userId)) {
            event.getParticipants().remove(user);
            return mapper.map(eventRepository.save(event), EventDto.class);
        }
        return mapper.map(event, EventDto.class);
    }

    /**
     * @param email
     * @param pageable pagination information
     * @return events
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDto> findMyEvents(String email, Pageable pageable) {
        Page<Event> eventPage = eventRepository.findByAuthorId(
                userService.findUserByEmail(email).getId(), pageable);
        return eventPage.map(event -> mapper.map(event, EventDto.class));
    }

    /**
     * @param event event
     * @param id    event author ID
     */
    private void checkEventAuthor(Event event, Long id) {
        if (!event.getAuthor().getId().equals(id)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

}
