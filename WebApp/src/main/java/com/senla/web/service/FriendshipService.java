package com.senla.web.service;

import com.senla.web.dto.friendship.FriendDto;
import com.senla.web.dto.friendship.FriendshipDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface FriendshipService {

    void sendFriendshipRequest(UUID friendId);

    List<FriendDto> getFriends();

    void deleteFriend(UUID friendshipId);

    Page<FriendshipDto> getPendingRequests();

    void acceptFriendshipRequest(UUID friendshipId);

    Page<FriendshipDto> getOutgoingRequests();
}
