package com.senla.rest.controller;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.api.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/friendships")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    /**
     *
     * @param friendshipId friendship ID
     * @return friendship
     */
    @GetMapping("{friendshipId}")
    public FriendshipDto getFriendshipById(@PathVariable Long friendshipId) {
        return friendshipService.getFriendshipById(friendshipId);
    }

    /**
     *
     * @param pageable pagination information
     * @return friend request list
     */
    @GetMapping("requests")
    public Page<FriendshipDto> findMyFriendshipRequests(Pageable pageable) {
        return friendshipService.findMyFriendshipRequests(pageable);
    }

    /**
     *
     * @param friendId user ID
     * @return friendship
     */
    @PostMapping
    public FriendshipDto addToFriends(@RequestParam Long friendId) {
        return friendshipService.createFriendship(friendId);
    }

    /**
     *
     * @param friendshipId friendship ID
     * @return accepted friendship
     */
    @PutMapping("{friendshipId}")
    public FriendshipDto acceptFriendship(@PathVariable Long friendshipId) {
        return friendshipService.acceptFriendship(friendshipId);
    }

    /**
     *
     * @param friendshipId friendship ID
     */
    @DeleteMapping("{friendshipId}")
    public void declineFriendship(@PathVariable Long friendshipId) {
        friendshipService.deleteFriendship(friendshipId);
    }

    /**
     *
     * @param pageable pagination information
     * @return friendships
     */
    @GetMapping
    public Page<FriendshipDto> findMyFriends(Pageable pageable) {
        return friendshipService.findAll(pageable);
    }
}
