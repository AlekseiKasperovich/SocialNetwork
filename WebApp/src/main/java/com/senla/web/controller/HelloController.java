package com.senla.web.controller;

import com.senla.web.security.SecurityUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        if (SecurityUtil.isAuthenticated()
                && SecurityContextHolder.getContext().getAuthentication().getClass()
                        != AnonymousAuthenticationToken.class
                && SecurityUtil.getCurrentUser().getPassword() != null) {
            return "redirect:/users/profile";
        }
        return "redirect:/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
