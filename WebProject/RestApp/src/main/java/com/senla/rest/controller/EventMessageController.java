package com.senla.rest.controller;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.service.EventMessageService;
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
@RequestMapping("/api/events/{eventId}/messages")
@RequiredArgsConstructor
public class EventMessageController {

    private final EventMessageService eventMessageService;

    /**
     *
     * @param eventId event ID
     * @param createMessageDto message body
     * @return message
     */
    @PostMapping
    public EventMessageDto createMessage(@PathVariable Long eventId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return eventMessageService.createEventMessage(eventId,
                createMessageDto);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @return message
     */
    @GetMapping("{messageId}")
    public EventMessageDto getMessageById(@PathVariable Long eventId,
            @PathVariable Long messageId) {
        return eventMessageService.getEventMessageById(eventId, messageId);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @PutMapping("{messageId}")
    public EventMessageDto updateMessage(@PathVariable Long eventId,
            @PathVariable Long messageId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return eventMessageService.updateEventMessage(eventId, messageId,
                createMessageDto);
    }

    /**
     *
     * @param eventId event ID
     * @param messageId message ID
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long eventId, @PathVariable Long messageId) {
        eventMessageService.deleteEventMessage(eventId, messageId);
    }

    /**
     *
     * @param eventId event ID
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<EventMessageDto> findAllMessages(@PathVariable Long eventId,
            Pageable pageable) {
        return eventMessageService.findAll(eventId, pageable);
    }

}
