package com.senla.web.controller;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.service.AdminUserService;
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
@RequestMapping(value = "admin")
public class AdminUserController {

    private final AdminUserService adminUserService;

    private static final String MESSAGE = "message";

    @GetMapping
    public String home(Model model) {
        model.addAttribute("welcome", "Welcome Admin!");
        return "admin";
    }

    @PostMapping("block/{userId}")
    public String blockUser(@PathVariable UUID userId, RedirectAttributes redirectAttributes) {
        try {
            adminUserService.blockUser(userId);
        } catch (MyAccessDeniedException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/users?fail";
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "User blocked successfully");
        return "redirect:/users?success";
    }

    @GetMapping("unblock")
    public String getOutgoingRequests(Model model) {
        Page<DtoUser> pageUsers = adminUserService.getBlockedUsers();
        List<DtoUser> users = pageUsers.getContent();
        model.addAttribute("users", users);
        return "blockedUsers";
    }

    @PostMapping("unblock/{userId}")
    public String blockUser(@PathVariable UUID userId) {
        adminUserService.unblockUser(userId);
        return "redirect:/admin/unblock";
    }
}
