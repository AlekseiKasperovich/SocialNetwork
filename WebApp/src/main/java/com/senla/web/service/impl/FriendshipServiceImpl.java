package com.senla.web.service.impl;

import com.senla.web.dto.friendship.FriendDto;
import com.senla.web.dto.friendship.FriendshipDto;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.feign.FriendshipClient;
import com.senla.web.security.SecurityUtil;
import com.senla.web.service.FriendshipService;
import feign.FeignException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipClient friendshipClient;

    @Override
    public void sendFriendshipRequest(UUID friendId) {
        try {
            friendshipClient.sendFriendshipRequest(friendId);
        } catch (FeignException.Forbidden ex) {
            throw new MyAccessDeniedException("You cannot send a request to yourself");
        } catch (FeignException.Conflict ex) {
            throw new MyAccessDeniedException(
                    "You have already sent a friend request or this is already your friend");
        }
    }

    @Override
    public List<FriendDto> getFriends() {
        Pageable page = PageRequest.of(0, 20);
        Page<FriendshipDto> pageFriendship = friendshipClient.getFriends(page).getBody();
        List<FriendshipDto> friendships = pageFriendship.getContent();
        List<FriendDto> friends = new ArrayList<>();
        friendships.forEach(
                f -> {
                    FriendDto friendDto =
                            FriendDto.builder()
                                    .id(f.getId())
                                    .friend(
                                            f.getSender()
                                                            .getId()
                                                            .equals(
                                                                    SecurityUtil.getCurrentUser()
                                                                            .getId())
                                                    ? f.getReceiver()
                                                    : f.getSender())
                                    .build();
                    friends.add(friendDto);
                });
        return friends;
    }

    @Override
    public void deleteFriend(UUID friendshipId) {
        friendshipClient.deleteFriend(friendshipId);
    }

    @Override
    public Page<FriendshipDto> getPendingRequests() {
        Pageable page = PageRequest.of(0, 20);
        return friendshipClient.getPendingRequests(page).getBody();
    }

    @Override
    public void acceptFriendshipRequest(UUID friendshipId) {
        friendshipClient.acceptFriendship(friendshipId);
    }

    @Override
    public Page<FriendshipDto> getOutgoingRequests() {
        Pageable page = PageRequest.of(0, 20);
        return friendshipClient.getOutgoingRequests(page).getBody();
    }
}
