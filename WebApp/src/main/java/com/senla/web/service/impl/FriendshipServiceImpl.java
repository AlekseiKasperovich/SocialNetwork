package com.senla.web.service.impl;

import com.senla.web.dto.friendship.FriendshipDto;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.feign.FriendshipClient;
import com.senla.web.service.FriendshipService;
import feign.FeignException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<FriendshipDto> getFriends() {
        return friendshipClient.getFriends();
    }
}
