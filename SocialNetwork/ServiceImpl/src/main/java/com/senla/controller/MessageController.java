package com.senla.controller;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import com.senla.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    /**
     * @param messageId message ID
     * @param email email
     * @return message
     */
    @GetMapping("{messageId}")
    public MessageDto getMessageById(@PathVariable Long messageId,
                                     @RequestHeader("${request.email}") String email) {
        return messageService.getMessageById(messageId, email);
    }

    /**
     * @param receiverId       receiver ID
     * @param createMessageDto message body
     * @param email email
     * @return message
     */
    @PostMapping
    public MessageDto createMessage(@RequestParam Long receiverId,
                                    @RequestBody CreateMessageDto createMessageDto,
                                    @RequestHeader("${request.email}") String email) {
        return messageService.createMessage(receiverId, createMessageDto, email);
    }

    /**
     * @param messageId        message ID
     * @param createMessageDto message body
     * @param email email
     * @return updated message
     */
    @PutMapping("{messageId}")
    public MessageDto updateMessage(@PathVariable Long messageId,
                                    @RequestBody CreateMessageDto createMessageDto,
                                    @RequestHeader("${request.email}") String email) {
        return messageService.updateMessage(messageId, createMessageDto, email);
    }

    /**
     * @param messageId message ID
     * @param email email
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long messageId,
                              @RequestHeader("${request.email}") String email) {
        messageService.deleteMessage(messageId, email);
    }

    /**
     * @param receiverId receiver ID
     * @param email email
     * @param pageable   pagination information
     * @return messages
     */
    @GetMapping
    public Page<MessageDto> findMyMessages(@RequestParam Long receiverId,
                                           @RequestHeader("${request.email}") String email, Pageable pageable) {
        return messageService.findAll(receiverId, email, pageable);
    }

}
