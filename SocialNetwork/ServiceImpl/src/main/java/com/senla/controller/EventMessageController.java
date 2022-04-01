package com.senla.controller;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.service.EventMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/events/{eventId}/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventMessageController {

    private final EventMessageService eventMessageService;

    /**
     * @param eventId          event ID
     * @param createMessageDto message body
     * @param email email
     * @return message
     */
    @PostMapping
    public EventMessageDto createMessage(@PathVariable Long eventId,
                                         @RequestBody CreateMessageDto createMessageDto,
                                         @RequestHeader("${request.email}") String email) {
        return eventMessageService.createEventMessage(eventId,
                createMessageDto, email);
    }

    /**
     * @param eventId   event ID
     * @param messageId message ID
     * @param email email
     * @return message
     */
    @GetMapping("{messageId}")
    public EventMessageDto getMessageById(@PathVariable Long eventId,
                                          @PathVariable Long messageId,
                                          @RequestHeader("${request.email}") String email) {
        return eventMessageService.getEventMessageById(eventId, messageId,
                email);
    }

    /**
     * @param eventId          event ID
     * @param messageId        message ID
     * @param createMessageDto message body
     * @param email email
     * @return updated message
     */
    @PutMapping("{messageId}")
    public EventMessageDto updateMessage(@PathVariable Long eventId,
                                         @PathVariable Long messageId,
                                         @RequestBody CreateMessageDto createMessageDto,
                                         @RequestHeader("${request.email}") String email) {
        return eventMessageService.updateEventMessage(eventId, messageId,
                createMessageDto, email);
    }

    /**
     * @param eventId   event ID
     * @param messageId message ID
     * @param email email
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long eventId, @PathVariable Long messageId,
                              @RequestHeader("${request.email}") String email) {
        eventMessageService.deleteEventMessage(eventId, messageId, email);
    }

    /**
     * @param eventId  event ID
     * @param email email
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<EventMessageDto> findAllMessages(@PathVariable Long eventId,
                                                 @RequestHeader("${request.email}") String email, Pageable pageable) {
        return eventMessageService.findAll(eventId, email, pageable);
    }

}
