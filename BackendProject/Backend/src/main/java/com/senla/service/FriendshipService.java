package com.senla.service;

import com.senla.dto.friendship.FriendshipDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface FriendshipService {

    FriendshipDto getFriendshipById(Long friendshipId, Long id);

    FriendshipDto createFriendship(Long friendId, Long id);

    FriendshipDto acceptFriendship(Long friendshipId, Long id);

    void deleteFriendship(Long friendshipId, Long id);

    Page<FriendshipDto> findAll(Long id, Pageable pageable);

    Page<FriendshipDto> findMyFriendshipRequests(Long id, Pageable pageable);

}
