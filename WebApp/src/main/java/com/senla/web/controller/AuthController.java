package com.senla.web.controller;

import com.senla.web.dto.profile.ChangePasswordDto;
import com.senla.web.dto.profile.EmailDto;
import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.ForgotPasswordDto;
import com.senla.web.dto.user.LoginUserDto;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.exception.MyServerErrorException;
import com.senla.web.exception.UserAlreadyExistException;
import com.senla.web.exception.UserNotFoundException;
import com.senla.web.security.CurrentUserDetails;
import com.senla.web.security.SecurityUtil;
import com.senla.web.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private static final String MESSAGE = "message";

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
            redirectAttributes.addFlashAttribute(MESSAGE, "Passwords do not match!");
            return "redirect:/registration?fail";
        }
        try {
            authService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/registration?fail";
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "Registration completed successfully!");
        return "redirect:/login?success";
    }

    @GetMapping("login")
    public String showLoginForm(Model model) {
        LoginUserDto user = new LoginUserDto();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("login/save")
    public String login(
            @ModelAttribute("user") @Valid LoginUserDto loginUserDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "login";
        }
        try {
            authService.login(loginUserDto);
        } catch (MyAccessDeniedException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/login?fail";
        }
        return "redirect:/users/profile";
    }

    @GetMapping("password/new")
    public String showEmailForm(Model model) {
        ForgotPasswordDto email = new ForgotPasswordDto();
        model.addAttribute("email", email);
        return "resetPassword";
    }

    @PostMapping("password/new/save")
    public String newPassword(
            @ModelAttribute("email") @Valid ForgotPasswordDto forgotPasswordDto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "action") String action) {
        if (result.hasErrors()) {
            return "resetPassword";
        }
        try {
            authService.resetPassword(forgotPasswordDto, action);
        } catch (UserNotFoundException | MyServerErrorException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/password/new?fail";
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "Please check your email!");
        return "redirect:/password/new?success";
    }

    @GetMapping("password/reset/{token}")
    public String showResetPasswordForm(
            @PathVariable String token, Model model, RedirectAttributes redirectAttributes) {
        EmailDto emailDto;
        try {
            emailDto = authService.validateToken(new TokenDto(null, token, null));
        } catch (MyAccessDeniedException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/password/new?fail";
        }
        CurrentUserDetails currentUserDetails =
                CurrentUserDetails.builder()
                        .email(emailDto.getEmail())
                        .authorities(SecurityUtil.mapRoleToAuthorities("ROLE_RESET_PASSWORD"))
                        .build();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        currentUserDetails, null, currentUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        model.addAttribute("password", changePasswordDto);
        return "resetPasswordForm";
    }

    @GetMapping("reset/password")
    public String showResetPasswordForm(Model model) {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        model.addAttribute("password", changePasswordDto);
        return "resetPasswordForm";
    }

    @PostMapping("reset/password")
    public String resetPassword(
            @ModelAttribute("password") @Valid ChangePasswordDto changePasswordDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "resetPasswordForm";
        }
        if (!changePasswordDto.getPassword().equals(changePasswordDto.getMatchingPassword())) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Passwords do not match!");
            return "redirect:/reset/password?fail";
        }
        if (SecurityUtil.isAuthenticated()) {
            String email = SecurityUtil.getCurrentUser().getUsername();
            authService.changePassword(changePasswordDto, email);
        }
        SecurityContextHolder.getContext()
                .setAuthentication(SecurityUtil.createAnonymousPrincipal());
        redirectAttributes.addFlashAttribute(
                MESSAGE, "Password change successfully! Please login with your new password!");
        return "redirect:/login?success";
    }
}
