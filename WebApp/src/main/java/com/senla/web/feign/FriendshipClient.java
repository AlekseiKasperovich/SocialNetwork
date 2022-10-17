package com.senla.web.feign;

import com.senla.web.dto.friendship.FriendshipDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FriendshipClient", url = "${request.host}" + "/friendships")
public interface FriendshipClient {

    @PostMapping
    void sendFriendshipRequest(@RequestParam UUID friendId);

    @GetMapping
    ResponseEntity<Page<FriendshipDto>> getFriends(Pageable page);

    @DeleteMapping("{friendshipId}")
    void deleteFriend(@PathVariable UUID friendshipId);

    @GetMapping("requests")
    ResponseEntity<Page<FriendshipDto>> getPendingRequests(Pageable page);

    @PutMapping("{friendshipId}")
    void acceptFriendship(@PathVariable UUID friendshipId);

    @GetMapping("requests/outgoing")
    ResponseEntity<Page<FriendshipDto>> getOutgoingRequests(Pageable page);
}
