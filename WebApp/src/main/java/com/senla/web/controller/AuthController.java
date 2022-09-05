package com.senla.web.controller;

import com.senla.web.dto.user.DtoCreateUser;

import javax.validation.Valid;

import com.senla.web.exception.UserAlreadyExistException;
import com.senla.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        DtoCreateUser user = new DtoCreateUser();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("registration/save")
    public String registration(
            @ModelAttribute("user") @Valid DtoCreateUser userDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            authService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException ex) {
            model.addAttribute("message", ex.getMessage());
            return "redirect:/registration?fail";
        }
        return "redirect:/registration?success";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("user") @Valid DtoCreateUser userDto,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        return null;
    }
}
