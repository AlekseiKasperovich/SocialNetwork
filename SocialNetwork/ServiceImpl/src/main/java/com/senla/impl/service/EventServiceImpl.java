package com.senla.impl.service;

import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.api.service.EventService;
import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.Event;
import com.senla.impl.model.User;
import com.senla.impl.repository.EventRepository;
import com.senla.impl.service.custom.CustomEventService;
import com.senla.impl.service.custom.CustomFriendshipService;
import com.senla.impl.service.custom.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
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
     *
     * @param eventId event ID
     * @return event
     */
    @Override
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId) {
        Event event = eventService.findEventById(eventId);
        eventService.checkUserOnEvent(userService.getCurrentUser(), event);
        return mapper.map(event, EventDto.class);
    }

    /**
     *
     * @param createEventDto event name and description
     * @return event
     */
    @Override
    public EventDto createEvent(CreateEventDto createEventDto) {
        Event event = mapper.map(createEventDto, Event.class);
        User author = userService.getCurrentUser();
        event.setAuthor(author);
        event.getParticipants().add(author);
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    /**
     *
     * @param eventId event ID
     * @param createEventDto event name and description
     * @return updated event
     */
    @Override
    public EventDto updateEvent(Long eventId, CreateEventDto createEventDto) {
        Event event = eventService.findEventById(eventId);
        checkEventAuthor(event, userService.getCurrentUser().getId());
        mapper.map(createEventDto, event);
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    /**
     *
     * @param eventId event ID
     */
    @Override
    public void deleteEvent(Long eventId) {
        Event event = eventService.findEventById(eventId);
        checkEventAuthor(event, userService.getCurrentUser().getId());
        eventRepository.deleteById(eventId);
    }

    /**
     *
     * @param eventId event ID
     * @param userId user ID
     * @return event
     */
    @Override
    public EventDto addUser(Long eventId, Long userId) {
        Event event = eventService.findEventById(eventId);
        User author = userService.getCurrentUser();
        checkEventAuthor(event, author.getId());
        User user = userService.findUserById(userId);
        if (friendshipService.checkFriendship(author.getId(), user.getId())) {
            event.getParticipants().add(user);
            return mapper.map(eventRepository.save(event), EventDto.class);
        }
        return mapper.map(event, EventDto.class);
    }

    /**
     *
     * @param eventId event ID
     * @param userId user ID
     * @return event
     */
    @Override
    public EventDto deleteUser(Long eventId, Long userId) {
        Event event = eventService.findEventById(eventId);
        User author = userService.getCurrentUser();
        checkEventAuthor(event, author.getId());
        User user = userService.findUserById(userId);
        if (!author.getId().equals(userId)) {
            event.getParticipants().remove(user);
            return mapper.map(eventRepository.save(event), EventDto.class);
        }
        return mapper.map(event, EventDto.class);
    }

    /**
     *
     * @param pageable pagination information
     * @return events
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDto> findMyEvents(Pageable pageable) {
        Page<Event> eventPage = eventRepository.findByAuthorId(
                userService.getCurrentUser().getId(), pageable);
        return eventPage.map(event -> mapper.map(event, EventDto.class));
    }

    /**
     *
     * @param event event
     * @param id event author ID
     */
    private void checkEventAuthor(Event event, Long id) {
        if (!event.getAuthor().getId().equals(id)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

}
