package com.senla.web.service;

import com.senla.web.dto.friendship.FriendshipDto;

import java.util.UUID;

import org.springframework.data.domain.Page;

public interface FriendshipService {

    void sendFriendshipRequest(UUID friendId);

    Page<FriendshipDto> getFriends();

    void deleteFriend(UUID friendshipId);

    Page<FriendshipDto> getPendingRequests();
}
