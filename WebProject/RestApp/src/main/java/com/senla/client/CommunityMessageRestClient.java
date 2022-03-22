package com.senla.client;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityMessageRestClient {

    CommunityMessageDto createCommunityMessage(Long communityId, CreateMessageDto createMessageDto);

    CommunityMessageDto getCommunityMessageById(Long communityId, Long messageId);

    CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId, CreateMessageDto createMessageDto);

    void deleteCommunityMessage(Long communityId, Long messageId);

    Page<CommunityMessageDto> findAll(Long communityId, Pageable pageable, HttpServletRequest request);
    
}
