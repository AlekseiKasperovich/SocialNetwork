package com.senla.service.impl;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.mapper.Mapper;
import com.senla.model.Friendship;
import com.senla.model.User;
import com.senla.repository.FriendshipRepository;
import com.senla.service.CustomFriendshipService;
import com.senla.service.CustomUserService;
import com.senla.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FriendshipServiceImpl implements FriendshipService {

    private final CustomFriendshipService friendshipService;
    private final CustomUserService userService;
    private final FriendshipRepository friendshipRepository;
    private final Mapper mapper;

    /**
     * @param friendshipId friendship ID
     * @param email email
     * @return friendship
     */
    @Override
    @Transactional(readOnly = true)
    public FriendshipDto getFriendshipById(Long friendshipId, String email) {
        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
        User user = userService.findUserByEmail(email);
        if (friendship.getReceiver().getId().equals(user.getId())
                || friendship.getSender().getId().equals(user.getId())) {
            return mapper.map(friendship, FriendshipDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     * @param friendId user ID
     * @param email email
     * @return friendship
     */
    @Override
    public FriendshipDto createFriendship(Long friendId, String email) {
        User user = userService.findUserByEmail(email);
        if (friendshipRepository.findFriendshipRequest(user.getId(), friendId).isEmpty()) {
            Friendship friendship = Friendship
                    .builder()
                    .sender(user)
                    .receiver(userService.findUserById(friendId))
                    .accepted(Boolean.FALSE)
                    .build();
            return mapper.map(friendshipRepository.save(friendship), FriendshipDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     * @param friendshipId friendship ID
     * @param email email
     * @return accepted friendship
     */
    @Override
    public FriendshipDto acceptFriendship(Long friendshipId, String email) {
        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
        User user = userService.findUserByEmail(email);
        if (friendship.getReceiver().getId().equals(user.getId())) {
            friendship.setAccepted(Boolean.TRUE);
            return mapper.map(friendshipRepository.save(friendship), FriendshipDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     * @param friendshipId friendship ID
     * @param email email
     */
    @Override
    public void deleteFriendship(Long friendshipId, String email) {
        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
        User user = userService.findUserByEmail(email);
        if (friendship.getReceiver().getId().equals(user.getId())
                || friendship.getSender().getId().equals(user.getId())) {
            friendshipRepository.deleteById(friendshipId);
        } else {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

    /**
     * @param email email
     * @param pageable pagination information
     * @return friendships
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FriendshipDto> findAll(String email, Pageable pageable) {
        User user = userService.findUserByEmail(email);
        Page<Friendship> friendshipPage = friendshipRepository.findMyFriends(
                user.getId(), pageable);
        return friendshipPage.map(friendship -> mapper.map(friendship,
                FriendshipDto.class));
    }

    /**
     * @param email email
     * @param pageable pagination information
     * @return friend request list
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FriendshipDto> findMyFriendshipRequests(String email, Pageable pageable) {
        User user = userService.findUserByEmail(email);
        Page<Friendship> friendshipPage = friendshipRepository.getMyFriendshipRequests(
                user.getId(), pageable);
        return friendshipPage.map(friendship -> mapper.map(friendship,
                FriendshipDto.class));
    }

}
