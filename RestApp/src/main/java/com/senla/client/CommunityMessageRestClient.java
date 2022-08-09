package com.senla.client;

import com.senla.dto.community.CommunityMessageDto;
import com.senla.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityMessageRestClient {

    CommunityMessageDto createCommunityMessage(Long communityId, CreateMessageDto createMessageDto);

    CommunityMessageDto getCommunityMessageById(Long communityId, Long messageId);

    CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId, CreateMessageDto createMessageDto);

    void deleteCommunityMessage(Long communityId, Long messageId);

    Page<CommunityMessageDto> findAll(Long communityId, Pageable pageable, HttpServletRequest request);

}
