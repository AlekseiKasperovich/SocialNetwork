package com.senla.controller;

import com.senla.dto.message.CreateMessageDto;
import com.senla.dto.message.MessageDto;
import com.senla.service.MessageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    /**
     * @param messageId message ID
     * @param id id
     * @return message
     */
    @GetMapping("{messageId}")
    public MessageDto getMessageById(
            @PathVariable Long messageId, @RequestHeader("${request.id}") Long id) {
        return messageService.getMessageById(messageId, id);
    }

    /**
     * @param receiverId receiver ID
     * @param createMessageDto message body
     * @param id id
     * @return message
     */
    @PostMapping
    public MessageDto createMessage(
            @RequestParam Long receiverId,
            @RequestBody CreateMessageDto createMessageDto,
            @RequestHeader("${request.id}") Long id) {
        return messageService.createMessage(receiverId, createMessageDto, id);
    }

    /**
     * @param messageId message ID
     * @param createMessageDto message body
     * @param id id
     * @return updated message
     */
    @PutMapping("{messageId}")
    public MessageDto updateMessage(
            @PathVariable Long messageId,
            @RequestBody CreateMessageDto createMessageDto,
            @RequestHeader("${request.id}") Long id) {
        return messageService.updateMessage(messageId, createMessageDto, id);
    }

    /**
     * @param messageId message ID
     * @param id id
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(
            @PathVariable Long messageId, @RequestHeader("${request.id}") Long id) {
        messageService.deleteMessage(messageId, id);
    }

    /**
     * @param receiverId receiver ID
     * @param id id
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<MessageDto> findMyMessages(
            @RequestParam Long receiverId,
            @RequestHeader("${request.id}") Long id,
            Pageable pageable) {
        return messageService.findAll(receiverId, id, pageable);
    }
}
