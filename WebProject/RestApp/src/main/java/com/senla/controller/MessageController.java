package com.senla.controller;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import com.senla.client.MessageRestClient;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageController {

    private final MessageRestClient messageRestClient;

    /**
     * @param messageId message ID
     * @return message
     */
    @GetMapping("{messageId}")
    public MessageDto getMessageById(@PathVariable Long messageId) {
        return messageRestClient.getMessageById(messageId);
    }

    /**
     * @param receiverId       receiver ID
     * @param createMessageDto message body
     * @return message
     */
    @PostMapping
    public MessageDto createMessage(@RequestParam Long receiverId,
                                    @Valid @RequestBody CreateMessageDto createMessageDto) {
        return messageRestClient.createMessage(receiverId, createMessageDto);
    }

    /**
     * @param messageId        message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @PutMapping("{messageId}")
    public MessageDto updateMessage(@PathVariable Long messageId,
                                    @Valid @RequestBody CreateMessageDto createMessageDto) {
        return messageRestClient.updateMessage(messageId, createMessageDto);
    }

    /**
     * @param messageId message ID
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageRestClient.deleteMessage(messageId);
    }

    /**
     * @param receiverId receiver ID
     * @param pageable   pagination information
     * @param request request
     * @return messages
     */
    @GetMapping
    public Page<MessageDto> findMyMessages(@RequestParam Long receiverId,
                                           Pageable pageable, HttpServletRequest request) {
        return messageRestClient.findAll(receiverId, pageable, request);
    }

}
