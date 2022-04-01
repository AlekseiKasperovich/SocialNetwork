package com.senla.controller;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/friendships",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    /**
     * @param friendshipId friendship ID
     * @param email email
     * @return friendship
     */
    @GetMapping("{friendshipId}")
    public FriendshipDto getFriendshipById(@PathVariable Long friendshipId,
                                           @RequestHeader("${request.email}") String email) {
        return friendshipService.getFriendshipById(friendshipId, email);
    }

    /**
     * @param friendId user ID
     * @param email email
     * @return friendship
     */
    @PostMapping
    public FriendshipDto addToFriends(@RequestParam Long friendId,
                                      @RequestHeader("${request.email}") String email) {
        return friendshipService.createFriendship(friendId, email);
    }

    /**
     * @param friendshipId friendship ID
     * @param email email
     * @return accepted friendship
     */
    @PutMapping("{friendshipId}")
    public FriendshipDto acceptFriendship(@PathVariable Long friendshipId,
                                          @RequestHeader("${request.email}") String email) {
        return friendshipService.acceptFriendship(friendshipId, email);
    }

    /**
     * @param friendshipId friendship ID
     * @param email email
     */
    @DeleteMapping("{friendshipId}")
    public void declineFriendship(@PathVariable Long friendshipId,
                                  @RequestHeader("${request.email}") String email) {
        friendshipService.deleteFriendship(friendshipId, email);
    }

    /**
     * @param email email
     * @param pageable pagination information
     * @return friend request list
     */
    @GetMapping("requests")
    public Page<FriendshipDto> findMyFriendshipRequests(
            @RequestHeader("${request.email}") String email, Pageable pageable) {
        return friendshipService.findMyFriendshipRequests(email, pageable);
    }

    /**
     * @param email email
     * @param pageable pagination information
     * @return friendships
     */
    @GetMapping
    public Page<FriendshipDto> findMyFriends(
            @RequestHeader("${request.email}") String email, Pageable pageable) {
        return friendshipService.findAll(email, pageable);
    }
}
