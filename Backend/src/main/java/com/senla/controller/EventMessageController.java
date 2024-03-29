package com.senla.controller;

import com.senla.dto.event.EventMessageDto;
import com.senla.dto.message.CreateMessageDto;
import com.senla.service.EventMessageService;
import java.util.UUID;
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
        value = "/api/events/{eventId}/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventMessageController {

    private final EventMessageService eventMessageService;

    /**
     * @param eventId event ID
     * @param createMessageDto message body
     * @param id id
     * @return message
     */
    @PostMapping
    public EventMessageDto createMessage(
            @PathVariable UUID eventId,
            @RequestBody CreateMessageDto createMessageDto,
            @RequestHeader("${request.id}") UUID id) {
        return eventMessageService.createEventMessage(eventId, createMessageDto, id);
    }

    /**
     * @param eventId event ID
     * @param messageId message ID
     * @param id id
     * @return message
     */
    @GetMapping("{messageId}")
    public EventMessageDto getMessageById(
            @PathVariable UUID eventId,
            @PathVariable UUID messageId,
            @RequestHeader("${request.id}") UUID id) {
        return eventMessageService.getEventMessageById(eventId, messageId, id);
    }

    /**
     * @param eventId event ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @param id id
     * @return updated message
     */
    @PutMapping("{messageId}")
    public EventMessageDto updateMessage(
            @PathVariable UUID eventId,
            @PathVariable UUID messageId,
            @RequestBody CreateMessageDto createMessageDto,
            @RequestHeader("${request.id}") UUID id) {
        return eventMessageService.updateEventMessage(eventId, messageId, createMessageDto, id);
    }

    /**
     * @param eventId event ID
     * @param messageId message ID
     * @param id id
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(
            @PathVariable UUID eventId,
            @PathVariable UUID messageId,
            @RequestHeader("${request.id}") UUID id) {
        eventMessageService.deleteEventMessage(eventId, messageId, id);
    }

    /**
     * @param eventId event ID
     * @param id id
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<EventMessageDto> findAllMessages(
            @PathVariable UUID eventId,
            @RequestHeader("${request.id}") UUID id,
            Pageable pageable) {
        return eventMessageService.findAll(eventId, id, pageable);
    }
}
