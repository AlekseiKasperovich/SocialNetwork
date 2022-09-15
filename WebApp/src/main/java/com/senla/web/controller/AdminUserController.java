package com.senla.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {

    @GetMapping("admin")
    public String home(Model model) {
        model.addAttribute("welcome", "Welcome Admin!");
        return "admin";
    }
}
