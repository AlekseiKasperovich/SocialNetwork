package com.senla.web.feign;

import com.senla.web.dto.friendship.FriendshipDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FriendshipClient", url = "${request.host}")
public interface FriendshipClient {

    @PostMapping(value = "friendships")
    void sendFriendshipRequest(@RequestParam UUID friendId);

    Page<FriendshipDto> getFriends();
}
