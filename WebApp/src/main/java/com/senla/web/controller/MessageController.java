package com.senla.web.controller;

import com.senla.web.dto.message.MessageDto;
import com.senla.web.service.MessageService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("{userId}")
    public String showConversation(@PathVariable UUID userId, Model model) {
        Page<MessageDto> pageMessages = messageService.getMessages(userId);
        List<MessageDto> messages = pageMessages.getContent();
        model.addAttribute("messages", messages);
        return "conversation";
    }
}
