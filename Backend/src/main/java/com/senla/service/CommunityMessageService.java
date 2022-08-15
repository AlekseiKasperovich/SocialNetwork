package com.senla.service;

import com.senla.dto.community.CommunityMessageDto;
import com.senla.dto.message.CreateMessageDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface CommunityMessageService {

    CommunityMessageDto getCommunityMessageById(UUID communityId, UUID messageId, UUID id);

    CommunityMessageDto updateCommunityMessage(
            UUID communityId, UUID messageId, CreateMessageDto createMessageDto, UUID id);

    CommunityMessageDto createCommunityMessage(
            UUID communityId, CreateMessageDto createMessageDto, UUID id);

    void deleteCommunityMessage(UUID communityId, UUID messageId, UUID id);

    Page<CommunityMessageDto> findAll(UUID communityId, UUID id, Pageable pageable);
}
