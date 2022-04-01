package com.senla.service;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityMessageService {

    CommunityMessageDto getCommunityMessageById(Long communityId, Long messageId, String email);

    CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId,
                                               CreateMessageDto createMessageDto, String email);

    CommunityMessageDto createCommunityMessage(Long communityId,
                                               CreateMessageDto createMessageDto, String email);

    void deleteCommunityMessage(Long communityId, Long messageId, String email);

    Page<CommunityMessageDto> findAll(Long communityId, String email, Pageable pageable);

}
