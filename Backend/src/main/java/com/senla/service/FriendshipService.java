package com.senla.service;

import com.senla.dto.friendship.FriendshipDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface FriendshipService {

    FriendshipDto getFriendshipById(UUID friendshipId, UUID id);

    FriendshipDto createFriendship(UUID friendId, UUID id);

    FriendshipDto acceptFriendship(UUID friendshipId, UUID id);

    void deleteFriendship(UUID friendshipId, UUID id);

    Page<FriendshipDto> findAll(UUID id, Pageable pageable);

    Page<FriendshipDto> findMyFriendshipRequests(UUID id, Pageable pageable);

    Page<FriendshipDto> findOutgoingFriendshipRequests(UUID id, Pageable pageable);
}
