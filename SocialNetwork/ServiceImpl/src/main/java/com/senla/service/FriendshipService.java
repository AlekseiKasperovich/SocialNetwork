package com.senla.service;

import com.senla.api.dto.friendship.FriendshipDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface FriendshipService {

    FriendshipDto getFriendshipById(Long friendshipId, String email);

    FriendshipDto createFriendship(Long friendId, String email);

    FriendshipDto acceptFriendship(Long friendshipId, String email);

    void deleteFriendship(Long friendshipId, String email);

    Page<FriendshipDto> findAll(String email, Pageable pageable);

    Page<FriendshipDto> findMyFriendshipRequests(String email, Pageable pageable);

}
