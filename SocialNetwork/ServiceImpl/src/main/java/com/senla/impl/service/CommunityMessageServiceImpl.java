package com.senla.impl.service;

import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.exception.CommunityMessageNotFoundException;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.api.service.CommunityMessageService;
import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.Community;
import com.senla.impl.model.CommunityMessage;
import com.senla.impl.model.User;
import com.senla.impl.repository.CommunityMessageRepository;
import com.senla.impl.service.custom.CustomCommunityService;
import com.senla.impl.service.custom.CustomUserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CommunityMessageServiceImpl implements CommunityMessageService {

    private final CommunityMessageRepository communityMessageRepository;
    private final CustomUserService userService;
    private final CustomCommunityService communityService;
    private final Mapper mapper;

    /**
     *
     * @param id message ID
     * @return message
     */
    @Transactional(readOnly = true)
    public CommunityMessage findCommunityMessageById(Long id) {
        return communityMessageRepository.findById(id).orElseThrow(
                () -> new CommunityMessageNotFoundException(
                        String.format("Community message with id = %s is not found", id)));
    }

    /**
     *
     * @param communityId community ID
     * @param messageId message ID
     * @return message
     */
    @Override
    public CommunityMessageDto getCommunityMessageById(Long communityId,
            Long messageId) {
        communityService.checkUserOnCommunity(userService.getCurrentUser(),
                communityService.findCommunityById(communityId));
        return mapper.map(findCommunityMessageById(messageId), CommunityMessageDto.class);
    }

    /**
     *
     * @param communityId community ID
     * @param createMessageDto message body
     * @return message
     */
    @Override
    public CommunityMessageDto createCommunityMessage(Long communityId,
            CreateMessageDto createMessageDto) {
        Community community = communityService.findCommunityById(communityId);
        User sender = userService.getCurrentUser();
        communityService.checkUserOnCommunity(sender, community);
        CommunityMessage communityMessage = CommunityMessage.builder()
                .body(createMessageDto.getBody())
                .posted(LocalDateTime.now())
                .sender(sender)
                .community(community)
                .build();
        return mapper.map(communityMessageRepository.save(communityMessage),
                CommunityMessageDto.class);
    }

    /**
     *
     * @param communityId community ID
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @Override
    public CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId,
            CreateMessageDto createMessageDto) {
        User user = userService.getCurrentUser();
        communityService.checkUserOnCommunity(user, communityService.findCommunityById(communityId));
        CommunityMessage communityMessage = findCommunityMessageById(messageId);
        checkMessageAuthor(communityMessage, user.getId());
        communityMessage.setBody(createMessageDto.getBody());
        return mapper.map(communityMessageRepository.save(communityMessage),
                CommunityMessageDto.class);
    }

    /**
     *
     * @param communityId community ID
     * @param messageId message ID
     */
    @Override
    public void deleteCommunityMessage(Long communityId, Long messageId) {
        User user = userService.getCurrentUser();
        communityService.checkUserOnCommunity(user, communityService.findCommunityById(communityId));
        CommunityMessage communityMessage = findCommunityMessageById(messageId);
        checkMessageAuthor(communityMessage, user.getId());
        communityMessageRepository.deleteById(messageId);
    }

    /**
     *
     * @param communityId community ID
     * @param pageable pagination information
     * @return messages
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommunityMessageDto> findAll(Long communityId,
            Pageable pageable) {
        communityService.checkUserOnCommunity(userService.getCurrentUser(),
                communityService.findCommunityById(communityId));
        Page<CommunityMessage> communityMessagePage = communityMessageRepository.
                findByCommunityIdOrderByPostedDesc(communityId, pageable);
        return communityMessagePage.map(message -> mapper.map(message,
                CommunityMessageDto.class));
    }

    /**
     *
     * @param communityMessage community мessage
     * @param id message author ID
     */
    private void checkMessageAuthor(CommunityMessage communityMessage, Long id) {
        if (!communityMessage.getSender().getId().equals(id)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

}
