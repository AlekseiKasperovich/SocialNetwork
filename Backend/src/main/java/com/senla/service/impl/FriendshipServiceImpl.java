package com.senla.service.impl;

import com.senla.dto.friendship.FriendshipDto;
import com.senla.exception.MyAccessDeniedException;
import com.senla.mapper.MapStructMapper;
import com.senla.mapper.Mapper;
import com.senla.model.Friendship;
import com.senla.repository.FriendshipRepository;
import com.senla.service.CustomFriendshipService;
import com.senla.service.CustomUserService;
import com.senla.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class FriendshipServiceImpl implements FriendshipService {

    private final CustomFriendshipService friendshipService;
    private final CustomUserService userService;
    private final FriendshipRepository friendshipRepository;
    private final Mapper mapper;
    private final MapStructMapper mapStructMapper;

    private static final String EXCEPTION_MESSAGE = "Access is denied";

    /**
     * @param friendshipId friendship ID
     * @param id id
     * @return friendship
     */
    @Override
    @Transactional(readOnly = true)
    public FriendshipDto getFriendshipById(Long friendshipId, Long id) {
        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
        if (friendship.getReceiver().getId().equals(id)
                || friendship.getSender().getId().equals(id)) {
            return mapStructMapper.friendshipToDto(friendship);
            //            return mapper.map(friendship, FriendshipDto.class);
        }
        throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
    }

    /**
     * @param friendId user ID
     * @param id id
     * @return friendship
     */
    @Override
    public FriendshipDto createFriendship(Long friendId, Long id) {
        if (friendshipRepository.findFriendshipRequest(id, friendId).isEmpty()) {
            Friendship friendship =
                    Friendship.builder()
                            .sender(userService.findUserById(id))
                            .receiver(userService.findUserById(friendId))
                            .accepted(Boolean.FALSE)
                            .build();
            return mapStructMapper.friendshipToDto(friendshipRepository.save(friendship));
            //            return mapper.map(friendshipRepository.save(friendship),
            // FriendshipDto.class);
        }
        throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
    }

    /**
     * @param friendshipId friendship ID
     * @param id id
     * @return accepted friendship
     */
    @Override
    public FriendshipDto acceptFriendship(Long friendshipId, Long id) {
        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
        if (friendship.getReceiver().getId().equals(id)) {
            friendship.setAccepted(Boolean.TRUE);
            return mapStructMapper.friendshipToDto(friendshipRepository.save(friendship));
            //            return mapper.map(friendshipRepository.save(friendship),
            // FriendshipDto.class);
        }
        throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
    }

    /**
     * @param friendshipId friendship ID
     * @param id id
     */
    @Override
    public void deleteFriendship(Long friendshipId, Long id) {
        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
        if (friendship.getReceiver().getId().equals(id)
                || friendship.getSender().getId().equals(id)) {
            friendshipRepository.deleteById(friendshipId);
        } else {
            throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
        }
    }

    /**
     * @param id id
     * @param pageable pagination information
     * @return friendships
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FriendshipDto> findAll(Long id, Pageable pageable) {
        Page<Friendship> friendshipPage = friendshipRepository.findMyFriends(id, pageable);
        return friendshipPage.map(friendship -> mapper.map(friendship, FriendshipDto.class));
    }

    /**
     * @param id id
     * @param pageable pagination information
     * @return friend request list
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FriendshipDto> findMyFriendshipRequests(Long id, Pageable pageable) {
        Page<Friendship> friendshipPage =
                friendshipRepository.getMyFriendshipRequests(id, pageable);
        return friendshipPage.map(friendship -> mapper.map(friendship, FriendshipDto.class));
    }
}
