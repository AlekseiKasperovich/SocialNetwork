package com.senla.service.impl;

import com.senla.dto.event.CreateEventDto;
import com.senla.dto.event.EventDto;
import com.senla.exception.MyAccessDeniedException;
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
     * @param id id
     * @return event
     */
    @Override
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId, Long id) {
        Event event = eventService.findEventById(eventId);
        eventService.checkUserOnEvent(userService.findUserById(id), event);
        return mapper.map(event, EventDto.class);
    }

    /**
     * @param createEventDto event name and description
     * @param id id
     * @return event
     */
    @Override
    public EventDto createEvent(CreateEventDto createEventDto, Long id) {
        Event event = mapper.map(createEventDto, Event.class);
        User author = userService.findUserById(id);
        event.setAuthor(author);
        event.getParticipants().add(author);
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    /**
     * @param eventId        event ID
     * @param createEventDto event name and description
     * @param id id
     * @return updated event
     */
    @Override
    public EventDto updateEvent(Long eventId, CreateEventDto createEventDto, Long id) {
        Event event = eventService.findEventById(eventId);
        checkEventAuthor(event, id);
        mapper.map(createEventDto, event);
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    /**
     * @param eventId event ID
     * @param id id
     */
    @Override
    public void deleteEvent(Long eventId, Long id) {
        Event event = eventService.findEventById(eventId);
        checkEventAuthor(event, id);
        eventRepository.deleteById(eventId);
    }

    /**
     * @param eventId event ID
     * @param userId  user ID
     * @param id id
     * @return event
     */
    @Override
    public EventDto addUser(Long eventId, Long userId, Long id) {
        Event event = eventService.findEventById(eventId);
        User author = userService.findUserById(id);
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
     * @param id id
     * @return event
     */
    @Override
    public EventDto deleteUser(Long eventId, Long userId, Long id) {
        Event event = eventService.findEventById(eventId);
        User author = userService.findUserById(id);
        checkEventAuthor(event, author.getId());
        User user = userService.findUserById(userId);
        if (!author.getId().equals(userId)) {
            event.getParticipants().remove(user);
            return mapper.map(eventRepository.save(event), EventDto.class);
        }
        return mapper.map(event, EventDto.class);
    }

    /**
     * @param id id
     * @param pageable pagination information
     * @return events
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDto> findMyEvents(Long id, Pageable pageable) {
        Page<Event> eventPage = eventRepository.findByAuthorId(id, pageable);
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
