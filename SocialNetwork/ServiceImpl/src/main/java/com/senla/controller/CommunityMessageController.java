package com.senla.controller;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
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
     * @param email email
     * @return message
     */
    @PostMapping
    public CommunityMessageDto createMessage(@PathVariable Long communityId,
                                             @RequestBody CreateMessageDto createMessageDto,
                                             @RequestHeader("${request.email}") String email) {
        return communityMessageService.createCommunityMessage(communityId,
                createMessageDto, email);
    }

    /**
     * @param communityId community ID
     * @param messageId   message ID
     * @param email email
     * @return message
     */
    @GetMapping("{messageId}")
    public CommunityMessageDto getMessageById(@PathVariable Long communityId,
                                              @PathVariable Long messageId,
                                              @RequestHeader("${request.email}") String email) {
        return communityMessageService.getCommunityMessageById(communityId,
                messageId, email);
    }

    /**
     * @param communityId      community ID
     * @param messageId        message ID
     * @param createMessageDto message body
     * @param email email
     * @return updated message
     */
    @PutMapping("{messageId}")
    public CommunityMessageDto updateMessage(@PathVariable Long communityId,
                                             @PathVariable Long messageId,
                                             @RequestBody CreateMessageDto createMessageDto,
                                             @RequestHeader("${request.email}") String email) {
        return communityMessageService.updateCommunityMessage(communityId, messageId,
                createMessageDto, email);
    }

    /**
     * @param communityId community ID
     * @param messageId   message ID
     * @param email email
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long communityId,
                              @PathVariable Long messageId,
                              @RequestHeader("${request.email}") String email) {
        communityMessageService.deleteCommunityMessage(communityId, messageId,
                email);
    }

    /**
     * @param communityId community ID
     * @param email email
     * @param pageable    pagination information
     * @return messages
     */
    @GetMapping
    public Page<CommunityMessageDto> findAllMessages(@PathVariable Long communityId,
                                                     @RequestHeader("${request.email}") String email, Pageable pageable) {
        return communityMessageService.findAll(communityId, email, pageable);
    }
}
