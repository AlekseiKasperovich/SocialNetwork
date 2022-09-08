package com.senla.web.controller;

import com.senla.web.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "users/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public String showUserProfile(Model model) {
        model.addAttribute("profile", "User profile");
        return "profile";
    }
}
