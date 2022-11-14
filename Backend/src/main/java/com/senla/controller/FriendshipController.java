package com.senla.controller;

import com.senla.dto.friendship.FriendshipDto;
import com.senla.service.FriendshipService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/friendships",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    /**
     * @param friendshipId friendship ID
     * @param id id
     * @return friendship
     */
    @GetMapping("{friendshipId}")
    public FriendshipDto getFriendshipById(
            @PathVariable UUID friendshipId, @RequestHeader("${request.id}") UUID id) {
        return friendshipService.getFriendshipById(friendshipId, id);
    }

    /**
     * @param friendId user ID
     * @param id id
     * @return friendship
     */
    @PostMapping
    public FriendshipDto addToFriends(
            @RequestParam UUID friendId, @RequestHeader("${request.id}") UUID id) {
        return friendshipService.createFriendship(friendId, id);
    }

    /**
     * @param friendshipId friendship ID
     * @param id id
     * @return accepted friendship
     */
    @PutMapping("{friendshipId}")
    public FriendshipDto acceptFriendship(
            @PathVariable UUID friendshipId, @RequestHeader("${request.id}") UUID id) {
        return friendshipService.acceptFriendship(friendshipId, id);
    }

    /**
     * @param friendshipId friendship ID
     * @param id id
     */
    @DeleteMapping("{friendshipId}")
    public void declineFriendship(
            @PathVariable UUID friendshipId, @RequestHeader("${request.id}") UUID id) {
        friendshipService.deleteFriendship(friendshipId, id);
    }

    /**
     * @param id id
     * @param pageable pagination information
     * @return friend request list
     */
    @GetMapping("requests")
    public Page<FriendshipDto> findMyFriendshipRequests(
            @RequestHeader("${request.id}") UUID id, Pageable pageable) {
        return friendshipService.findMyFriendshipRequests(id, pageable);
    }

    @GetMapping("requests/outgoing")
    public Page<FriendshipDto> findOutgoingFriendshipRequests(
            @RequestHeader("${request.id}") UUID id, Pageable pageable) {
        return friendshipService.findOutgoingFriendshipRequests(id, pageable);
    }

    /**
     * @param id id
     * @param pageable pagination information
     * @return friendships
     */
    @GetMapping
    public Page<FriendshipDto> findMyFriends(
            @RequestHeader("${request.id}") UUID id, Pageable pageable) {
        return friendshipService.findMyFriends(id, pageable);
    }
}
