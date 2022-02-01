package com.senla.api.service;

import com.senla.api.dto.friendship.FriendshipDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface FriendshipService {

    FriendshipDto getFriendshipById(Long friendshipId);

    FriendshipDto createFriendship(Long friendId);

    FriendshipDto acceptFriendship(Long friendshipId);

    void deleteFriendship(Long friendshipId);

    Page<FriendshipDto> findAll(Pageable pageable);

    Page<FriendshipDto> findMyFriendshipRequests(Pageable pageable);

}
