package com.senla.rest.controller;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.service.CommunityMessageService;
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
@RequestMapping("/api/communities/{communityId}/messages")
@RequiredArgsConstructor
public class CommunityMessageController {

    private final CommunityMessageService communityMessageService;

    /**
     *
     * @param communityId community ID
     * @param createMessageDto message body
     * @return message
     */
    @PostMapping
    public CommunityMessageDto createMessage(@PathVariable Long communityId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return communityMessageService.createCommunityMessage(communityId,
                createMessageDto);
    }

    /**
     *
     * @param communityId community ID
     * @param messageId message ID
     * @return message
     */
    @GetMapping("{messageId}")
    public CommunityMessageDto getMessageById(@PathVariable Long communityId,
            @PathVariable Long messageId) {
        return communityMessageService.getCommunityMessageById(communityId, messageId);
    }

    /**
     *
     * @param communityId community ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @PutMapping("{messageId}")
    public CommunityMessageDto updateMessage(@PathVariable Long communityId,
            @PathVariable Long messageId,
            @Valid @RequestBody CreateMessageDto createMessageDto) {
        return communityMessageService.updateCommunityMessage(communityId, messageId,
                createMessageDto);
    }

    /**
     *
     * @param communityId community ID
     * @param messageId message ID
     */
    @DeleteMapping("{messageId}")
    public void deleteMessage(@PathVariable Long communityId, @PathVariable Long messageId) {
        communityMessageService.deleteCommunityMessage(communityId, messageId);
    }

    /**
     *
     * @param communityId community ID
     * @param pageable pagination information
     * @return messages
     */
    @GetMapping
    public Page<CommunityMessageDto> findAllMessages(@PathVariable Long communityId,
            Pageable pageable) {
        return communityMessageService.findAll(communityId, pageable);
    }
}
