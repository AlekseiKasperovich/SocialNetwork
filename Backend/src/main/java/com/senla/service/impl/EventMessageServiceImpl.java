package com.senla.service.impl;

import com.senla.dto.event.EventMessageDto;
import com.senla.dto.message.CreateMessageDto;
import com.senla.exception.EventMessageNotFoundException;
import com.senla.exception.MyAccessDeniedException;
import com.senla.mapper.Mapper;
import com.senla.model.Event;
import com.senla.model.EventMessage;
import com.senla.model.User;
import com.senla.repository.EventMessageRepository;
import com.senla.service.CustomEventService;
import com.senla.service.CustomUserService;
import com.senla.service.EventMessageService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class EventMessageServiceImpl implements EventMessageService {

    private final EventMessageRepository eventMessageRepository;
    private final CustomUserService userService;
    private final CustomEventService eventService;
    private final Mapper mapper;

    /**
     * @param id message ID
     * @return message
     */
    @Transactional(readOnly = true)
    public EventMessage findEventMessageById(Long id) {
        return eventMessageRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new EventMessageNotFoundException(
                                        String.format(
                                                "Event message with id = %s is not found", id)));
    }

    /**
     * @param eventId event ID
     * @param messageId message ID
     * @param id id
     * @return message
     */
    @Override
    public EventMessageDto getEventMessageById(Long eventId, Long messageId, Long id) {
        eventService.checkUserOnEvent(
                userService.findUserById(id), eventService.findEventById(eventId));
        return mapper.map(findEventMessageById(messageId), EventMessageDto.class);
    }

    /**
     * @param eventId event ID
     * @param createMessageDto message body
     * @param id id
     * @return message
     */
    @Override
    public EventMessageDto createEventMessage(
            Long eventId, CreateMessageDto createMessageDto, Long id) {
        Event event = eventService.findEventById(eventId);
        User sender = userService.findUserById(id);
        eventService.checkUserOnEvent(sender, event);
        EventMessage eventMessage =
                EventMessage.builder()
                        .body(createMessageDto.getBody())
                        .posted(LocalDateTime.now())
                        .sender(sender)
                        .event(event)
                        .build();
        return mapper.map(eventMessageRepository.save(eventMessage), EventMessageDto.class);
    }

    /**
     * @param eventId event ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @param id id
     * @return updated message
     */
    @Override
    public EventMessageDto updateEventMessage(
            Long eventId, Long messageId, CreateMessageDto createMessageDto, Long id) {
        User user = userService.findUserById(id);
        eventService.checkUserOnEvent(user, eventService.findEventById(eventId));
        EventMessage eventMessage = findEventMessageById(messageId);
        checkMessageAuthor(eventMessage, user.getId());
        eventMessage.setBody(createMessageDto.getBody());
        return mapper.map(eventMessageRepository.save(eventMessage), EventMessageDto.class);
    }

    /**
     * @param eventId event ID
     * @param messageId message ID
     * @param id id
     */
    @Override
    public void deleteEventMessage(Long eventId, Long messageId, Long id) {
        User user = userService.findUserById(id);
        eventService.checkUserOnEvent(user, eventService.findEventById(eventId));
        EventMessage eventMessage = findEventMessageById(messageId);
        checkMessageAuthor(eventMessage, user.getId());
        eventMessageRepository.deleteById(messageId);
    }

    /**
     * @param eventId event ID
     * @param id id
     * @param pageable pagination information
     * @return messages
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventMessageDto> findAll(Long eventId, Long id, Pageable pageable) {
        eventService.checkUserOnEvent(
                userService.findUserById(id), eventService.findEventById(eventId));
        Page<EventMessage> eventMessagePage =
                eventMessageRepository.findByEventIdOrderByPostedDesc(eventId, pageable);
        return eventMessagePage.map(message -> mapper.map(message, EventMessageDto.class));
    }

    /**
     * @param eventMessage event message
     * @param id message author ID
     */
    private void checkMessageAuthor(EventMessage eventMessage, Long id) {
        if (!eventMessage.getSender().getId().equals(id)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }
}
