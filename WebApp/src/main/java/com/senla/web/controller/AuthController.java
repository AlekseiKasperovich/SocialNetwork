package com.senla.web.controller;

import com.senla.web.dto.user.DtoCreateUser;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        DtoCreateUser user = new DtoCreateUser();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("registration/save")
    public String registration(
            @Valid @ModelAttribute("user") DtoCreateUser userDto,
            BindingResult result,
            Model model) {
        //        User existingUser = userService.findUserByEmail(userDto.getEmail());
        //
        //        if (existingUser != null && existingUser.getEmail() != null &&
        // !existingUser.getEmail().isEmpty()) {
        //            result.rejectValue("email", null,
        //                    "There is already an account registered with the same email");
        //        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "registration";
        }

        //        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
