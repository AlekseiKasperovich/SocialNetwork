package com.senla.controller;

import com.senla.client.CommunityMessageRestClient;
import com.senla.dto.community.CommunityMessageDto;
import com.senla.dto.message.CreateMessageDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/communities/{communityId}/messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommunityMessageController {

    private final CommunityMessageRestClient communityMessageRestClient;

    /**
     * @param communityId community ID
     * @param messageId message ID
     * @return message
     */
    @GetMapping("{messageId}")
    public CommunityMessageDto getMessageById(
            @PathVariable Long communityId, @PathVariable Long messageId) {
        return communityMessageRestClient.getCommunityMessageById(communityId, messageId);
    }

    /**
     * @param communityId community ID
     * @param createMessageDto message body
     * @return message
     */
    @PostMapping
    public CommunityMessageDto createMessage(
            @PathVariable Long communityId, @Valid @RequestBody CreateMessageDto createMessageDto) {
        return communityMessageRestClient.createCommunityMessage(communityId, createMessageDto);
    }

    /**
     * @param communityId community ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @PutMapping("{messageId}")
    public CommunityMessageDto updateMessage(
            @PathVariable Long communityId,
            @PathVariable Long messageId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return communityMessageRestClient.updateCommunityMessage(
                communityId, messageId, createMessageDto);
    }

    /**
     * @param communityId community ID
     * @param messageId message ID
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long communityId, @PathVariable Long messageId) {
        communityMessageRestClient.deleteCommunityMessage(communityId, messageId);
    }

    /**
     * @param communityId community ID
     * @param pageable pagination information
     * @param request request
     * @return messages
     */
    @GetMapping
    public Page<CommunityMessageDto> findAllMessages(
            @PathVariable Long communityId, Pageable pageable, HttpServletRequest request) {
        return communityMessageRestClient.findAll(communityId, pageable, request);
    }
}
