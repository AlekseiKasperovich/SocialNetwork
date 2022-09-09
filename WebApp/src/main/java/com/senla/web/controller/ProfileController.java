package com.senla.web.controller;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "users/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public String showUserProfile(Model model) {
        try {
            DtoUser user = profileService.getCurrentUserProfile();
            System.out.println(user);
        } catch (MyAccessDeniedException ex) {
            model.addAttribute("profile", ex.getMessage());
        }
        return "profile";
    }
}
