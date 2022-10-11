package com.senla.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequiredArgsConstructor
@RequestMapping(value = "messages")
public class MessageController {

    @GetMapping
    public String showChat(Model model) {
        return "chat";
    }
}
