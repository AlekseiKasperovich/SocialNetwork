package com.senla.web.controller;

import com.senla.web.dto.profile.ChangePasswordDto;
import com.senla.web.dto.profile.EmailDto;
import com.senla.web.dto.user.DtoUser;
import com.senla.web.security.SecurityUtil;
import com.senla.web.service.ProfileService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "users/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public String showUserProfile(Model model) {
        DtoUser user = profileService.getCurrentUserProfile();
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("password")
    public String showChangePasswordForm(Model model) {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        model.addAttribute("password", changePasswordDto);
        return "changePassword";
    }

    @PostMapping("password")
    public String changePassword(
            @ModelAttribute("password") @Valid ChangePasswordDto changePasswordDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "changePassword";
        }
        if (!changePasswordDto.getPassword().equals(changePasswordDto.getMatchingPassword())) {
            redirectAttributes.addFlashAttribute("message", "Passwords do not match!");
            return "redirect:/users/profile/password?fail";
        }
        profileService.changePassword(changePasswordDto);
        SecurityContextHolder.getContext()
                .setAuthentication(SecurityUtil.createAnonymousPrincipal());
        redirectAttributes.addFlashAttribute(
                "message", "Password change successfully! Please login with your new password!");
        return "redirect:/login?success";
    }

    @GetMapping("delete")
    public String showDeleteProfileForm(Model model) {
        EmailDto emailDto = new EmailDto();
        model.addAttribute("email", emailDto);
        return "deleteProfile";
    }

    @PostMapping("delete")
    public String deleteProfile(
            @ModelAttribute("email") @Valid EmailDto emailDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "deleteProfile";
        }
        if (SecurityUtil.isAuthenticated()) {
            if (SecurityUtil.getCurrentUser().getUsername().equals(emailDto.getEmail())) {
                profileService.deleteProfile();
                SecurityContextHolder.getContext()
                        .setAuthentication(SecurityUtil.createAnonymousPrincipal());
                redirectAttributes.addFlashAttribute(
                        "message",
                        "Profile delete successfully! Please register your new account!");
                return "redirect:/registration?success";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Don't joke, this is not your email!");
        return "redirect:/users/profile/delete?fail";
    }
}
