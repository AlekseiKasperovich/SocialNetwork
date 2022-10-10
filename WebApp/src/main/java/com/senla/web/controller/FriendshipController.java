package com.senla.web.controller;

import com.senla.web.dto.friendship.FriendshipDto;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.service.FriendshipService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    private static final String MESSAGE = "message";

    @PostMapping("add/{friendId}")
    public String addFriend(@PathVariable UUID friendId, RedirectAttributes redirectAttributes) {
        try {
            friendshipService.sendFriendshipRequest(friendId);
        } catch (MyAccessDeniedException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/users?fail";
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "Request sent successfully");
        return "redirect:/users?success";
    }

    @PostMapping("delete/{friendshipId}")
    public String deleteFriend(@PathVariable UUID friendshipId) {
        friendshipService.deleteFriend(friendshipId);
        return "redirect:/friends";
    }

    @GetMapping
    public String getFriends(Model model) {
        Page<FriendshipDto> pageFriendship = friendshipService.getFriends();
        List<FriendshipDto> friendships = pageFriendship.getContent();
        model.addAttribute("friendships", friendships);
        return "friends";
    }

    @GetMapping("requests/pending")
    public String getPendingRequests(Model model) {
        Page<FriendshipDto> pageFriendship = friendshipService.getPendingRequests();
        List<FriendshipDto> friendships = pageFriendship.getContent();
        model.addAttribute("pending", friendships);
        return "pendingRequests";
    }

    @PostMapping("accept/{friendshipId}")
    public String acceptFriendship(@PathVariable UUID friendshipId) {
        friendshipService.acceptFriendshipRequest(friendshipId);
        return "redirect:/friends/requests/pending";
    }

    @PostMapping("decline/{friendshipId}")
    public String declineFriendship(@PathVariable UUID friendshipId) {
        friendshipService.deleteFriend(friendshipId);
        return "redirect:/friends/requests/pending";
    }

    @GetMapping("requests/outgoing")
    public String getOutgoingRequests(Model model) {
        Page<FriendshipDto> pageFriendship = friendshipService.getOutgoingRequests();
        List<FriendshipDto> friendships = pageFriendship.getContent();
        model.addAttribute("outgoing", friendships);
        return "outgoingRequests";
    }

    @PostMapping("requests/outgoing/delete/{friendshipId}")
    public String deleteFriendship(@PathVariable UUID friendshipId) {
        friendshipService.deleteFriend(friendshipId);
        return "redirect:/friends/requests/outgoing";
    }
}
