package com.senla.client;

import com.senla.dto.friendship.FriendshipDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface FriendshipRestClient {

    FriendshipDto getFriendshipById(UUID friendshipId);

    Page<FriendshipDto> findMyFriendshipRequests(Pageable pageable, HttpServletRequest request);

    FriendshipDto createFriendship(UUID friendId);

    void deleteFriendship(UUID friendshipId);

    FriendshipDto acceptFriendship(UUID friendshipId);

    Page<FriendshipDto> findMyFriends(Pageable pageable, HttpServletRequest request);

    Page<FriendshipDto> findOutgoingFriendshipRequests(
            Pageable pageable, HttpServletRequest request);
}
