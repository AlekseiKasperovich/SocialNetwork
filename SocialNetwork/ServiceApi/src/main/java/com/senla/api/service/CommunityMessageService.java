package com.senla.api.service;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityMessageService {

    CommunityMessageDto getCommunityMessageById(Long communityId, Long messageId);

    CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId,
            CreateMessageDto createMessageDto);

    CommunityMessageDto createCommunityMessage(Long communityId,
            CreateMessageDto createMessageDto);

    void deleteCommunityMessage(Long communityId, Long messageId);

    Page<CommunityMessageDto> findAll(Long communityId, Pageable pageable);

}
