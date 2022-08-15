package com.senla.client;

import com.senla.dto.community.CommunityMessageDto;
import com.senla.dto.message.CreateMessageDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface CommunityMessageRestClient {

    CommunityMessageDto createCommunityMessage(UUID communityId, CreateMessageDto createMessageDto);

    CommunityMessageDto getCommunityMessageById(UUID communityId, UUID messageId);

    CommunityMessageDto updateCommunityMessage(
            UUID communityId, UUID messageId, CreateMessageDto createMessageDto);

    void deleteCommunityMessage(UUID communityId, UUID messageId);

    Page<CommunityMessageDto> findAll(
            UUID communityId, Pageable pageable, HttpServletRequest request);
}
