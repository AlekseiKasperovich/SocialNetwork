package com.senla.service;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityMessageService {

    CommunityMessageDto getCommunityMessageById(Long communityId, Long messageId, Long id);

    CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId,
                                               CreateMessageDto createMessageDto, Long id);

    CommunityMessageDto createCommunityMessage(Long communityId,
                                               CreateMessageDto createMessageDto, Long id);

    void deleteCommunityMessage(Long communityId, Long messageId, Long id);

    Page<CommunityMessageDto> findAll(Long communityId, Long id, Pageable pageable);

}
