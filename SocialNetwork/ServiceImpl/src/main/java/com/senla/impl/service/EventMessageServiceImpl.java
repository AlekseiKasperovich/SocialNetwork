package com.senla.impl.service;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.exception.EventMessageNotFoundException;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.api.service.EventMessageService;
import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.Event;
import com.senla.impl.model.EventMessage;
import com.senla.impl.model.User;
import com.senla.impl.repository.EventMessageRepository;
import com.senla.impl.service.custom.CustomEventService;
import com.senla.impl.service.custom.CustomUserService;
import java.time.LocalDateTime;
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
public class EventMessageServiceImpl implements EventMessageService {

    private final EventMessageRepository eventMessageRepository;
    private final CustomUserService userService;
    private final CustomEventService eventService;
    private final Mapper mapper;

    /**
     *
     * @param id message ID
     * @return message
     */
    @Transactional(readOnly = true)
    public EventMessage findEventMessageById(Long id) {
        return eventMessageRepository.findById(id).orElseThrow(
                () -> new EventMessageNotFoundException(
                        String.format("Event message with id = %s is not found", id)));
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @return message
     */
    @Override
    public EventMessageDto getEventMessageById(Long eventId, Long messageId) {
        eventService.checkUserOnEvent(userService.getCurrentUser(),
                eventService.findEventById(eventId));
        return mapper.map(findEventMessageById(messageId), EventMessageDto.class);
    }

    /**
     *
     * @param eventId event ID
     * @param createMessageDto message body
     * @return message
     */
    @Override
    public EventMessageDto createEventMessage(Long eventId,
            CreateMessageDto createMessageDto) {
        Event event = eventService.findEventById(eventId);
        User sender = userService.getCurrentUser();
        eventService.checkUserOnEvent(sender, event);
        EventMessage eventMessage = EventMessage.builder()
                .body(createMessageDto.getBody())
                .posted(LocalDateTime.now())
                .sender(sender)
                .event(event)
                .build();
        return mapper.map(eventMessageRepository.save(eventMessage),
                EventMessageDto.class);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @Override
    public EventMessageDto updateEventMessage(Long eventId, Long messageId,
            CreateMessageDto createMessageDto) {
        User user = userService.getCurrentUser();
        eventService.checkUserOnEvent(user, eventService.findEventById(eventId));
        EventMessage eventMessage = findEventMessageById(messageId);
        checkMessageAuthor(eventMessage, user.getId());
        eventMessage.setBody(createMessageDto.getBody());
        return mapper.map(eventMessageRepository.save(eventMessage),
                EventMessageDto.class);

    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     */
    @Override
    public void deleteEventMessage(Long eventId, Long messageId) {
        User user = userService.getCurrentUser();
        eventService.checkUserOnEvent(user, eventService.findEventById(eventId));
        EventMessage eventMessage = findEventMessageById(messageId);
        checkMessageAuthor(eventMessage, user.getId());
        eventMessageRepository.deleteById(messageId);

    }

    /**
     *
     * @param eventId event ID
     * @param pageable pagination information
     * @return messages
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventMessageDto> findAll(Long eventId, Pageable pageable) {
        eventService.checkUserOnEvent(userService.getCurrentUser(),
                eventService.findEventById(eventId));
        Page<EventMessage> eventMessagePage = eventMessageRepository.
                findByEventIdOrderByPostedDesc(eventId, pageable);
        return eventMessagePage.map(message -> mapper.map(message,
                EventMessageDto.class));
    }

    /**
     *
     * @param eventMessage event message
     * @param id message author ID
     */
    private void checkMessageAuthor(EventMessage eventMessage, Long id) {
        if (!eventMessage.getSender().getId().equals(id)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }
}
