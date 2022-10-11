package com.senla.web.controller;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.dto.user.SearchUserDto;
import com.senla.web.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showUsers(Model model) {
        SearchUserDto searchUserDto = new SearchUserDto();
        model.addAttribute("search", searchUserDto);
        Page<DtoUser> pageUsers = userService.getUsers();
        List<DtoUser> users = pageUsers.getContent();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("search")
    public String searchUsers(@ModelAttribute("search") SearchUserDto searchUserDto, Model model) {
        Page<DtoUser> pageUsers = userService.getUsers(searchUserDto);
        List<DtoUser> users = pageUsers.getContent();
        model.addAttribute("users", users);
        return "users";
    }
}
