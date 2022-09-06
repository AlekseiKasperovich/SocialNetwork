package com.senla.web.controller;

import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.LoginUserDto;
import com.senla.web.exception.UserAlreadyExistException;
import com.senla.web.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            redirectAttributes.addFlashAttribute("message", "Passwords do not match!");
            return "redirect:/registration?fail";
        }
        try {
            authService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/registration?fail";
        }
        return "redirect:/registration?success";
    }

    @GetMapping("login")
    public String login(Model model) {
        LoginUserDto user = new LoginUserDto();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("login/save")
    public String login(
            @ModelAttribute("user") @Valid LoginUserDto loginUserDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        TokenDto token = authService.login(loginUserDto);
        System.out.println(token);
        return null;
    }
}
