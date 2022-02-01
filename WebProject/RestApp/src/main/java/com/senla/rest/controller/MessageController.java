package com.senla.rest.controller;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import com.senla.api.service.MessageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    /**
     *
     * @param messageId message ID
     * @return message
     */
    @GetMapping("{messageId}")
    public MessageDto getMessageById(@PathVariable Long messageId) {
        return messageService.getMessageById(messageId);
    }

    /**
     *
     * @param receiverId receiver ID
     * @param createMessageDto message body
     * @return message 
     */
    @PostMapping
    public MessageDto createMessage(@RequestParam Long receiverId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return messageService.createMessage(receiverId, createMessageDto);
    }

    /**
     *
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @PutMapping("{messageId}")
    public MessageDto updateMessage(@PathVariable Long messageId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return messageService.updateMessage(messageId, createMessageDto);
    }

    /**
     *
     * @param messageId message ID
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
    }

    /**
     *
     * @param receiverId receiver ID
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<MessageDto> findMyMessages(@RequestParam Long receiverId,
            Pageable pageable) {
        return messageService.findAll(receiverId, pageable);
    }

}
