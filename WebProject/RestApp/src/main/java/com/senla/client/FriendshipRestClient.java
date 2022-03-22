package com.senla.client;

import com.senla.api.dto.friendship.FriendshipDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface FriendshipRestClient {

    FriendshipDto getFriendshipById(Long friendshipId);

    Page<FriendshipDto> findMyFriendshipRequests(Pageable pageable, HttpServletRequest request);

    FriendshipDto createFriendship(Long friendId);

    void deleteFriendship(Long friendshipId);

    FriendshipDto acceptFriendship(Long friendshipId);

    Page<FriendshipDto> findAll(Pageable pageable, HttpServletRequest request);

}
