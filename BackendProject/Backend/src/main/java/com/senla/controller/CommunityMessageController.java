package com.senla.controller;

import com.senla.dto.community.CommunityMessageDto;
import com.senla.dto.message.CreateMessageDto;
import com.senla.service.CommunityMessageService;
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

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/communities/{communityId}/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommunityMessageController {

    private final CommunityMessageService communityMessageService;

    /**
     * @param communityId      community ID
     * @param createMessageDto message body
     * @param id               id
     * @return message
     */
    @PostMapping
    public CommunityMessageDto createMessage(@PathVariable Long communityId,
                                             @RequestBody CreateMessageDto createMessageDto,
                                             @RequestHeader("${request.id}") Long id) {
        return communityMessageService.createCommunityMessage(communityId, createMessageDto, id);
    }

    /**
     * @param communityId community ID
     * @param messageId   message ID
     * @param id          id
     * @return message
     */
    @GetMapping("{messageId}")
    public CommunityMessageDto getMessageById(@PathVariable Long communityId,
                                              @PathVariable Long messageId,
                                              @RequestHeader("${request.id}") Long id) {
        return communityMessageService.getCommunityMessageById(communityId, messageId, id);
    }

    /**
     * @param communityId      community ID
     * @param messageId        message ID
     * @param createMessageDto message body
     * @param id               id
     * @return updated message
     */
    @PutMapping("{messageId}")
    public CommunityMessageDto updateMessage(@PathVariable Long communityId,
                                             @PathVariable Long messageId,
                                             @RequestBody CreateMessageDto createMessageDto,
                                             @RequestHeader("${request.id}") Long id) {
        return communityMessageService.updateCommunityMessage(communityId, messageId, createMessageDto, id);
    }

    /**
     * @param communityId community ID
     * @param messageId   message ID
     * @param id          id
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long communityId,
                              @PathVariable Long messageId,
                              @RequestHeader("${request.id}") Long id) {
        communityMessageService.deleteCommunityMessage(communityId, messageId, id);
    }

    /**
     * @param communityId community ID
     * @param id          id
     * @param pageable    pagination information
     * @return messages
     */
    @GetMapping
    public Page<CommunityMessageDto> findAllMessages(@PathVariable Long communityId,
                                                     @RequestHeader("${request.id}") Long id, Pageable pageable) {
        return communityMessageService.findAll(communityId, id, pageable);
    }
}
