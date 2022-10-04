package com.senla.web.controller;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showUsers(Model model) {
        Page<DtoUser> users = userService.getUsers();
        System.out.println(users);
        model.addAttribute("users", users);
        return "users";
    }
}
