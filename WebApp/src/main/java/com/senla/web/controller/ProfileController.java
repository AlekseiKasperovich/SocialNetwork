package com.senla.web.controller;

import com.senla.web.dto.profile.ChangePasswordDto;
import com.senla.web.dto.profile.EmailDto;
import com.senla.web.dto.profile.UpdateUserDto;
import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.ImageUploadException;
import com.senla.web.security.SecurityUtil;
import com.senla.web.service.ImageService;
import com.senla.web.service.ProfileService;
import com.senla.web.validator.ImageValidator;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "users/profile")
public class ProfileController {

    private final ProfileService profileService;

    private final ImageValidator imageValidator;
    private final ImageService imageService;

    private static final String MESSAGE = "message";

    @GetMapping
    public String showUserProfile(Model model) {
        DtoUser user = profileService.getCurrentUserProfile();
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("update")
    public String showUpdateProfileForm(Model model) {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        model.addAttribute("updateUser", updateUserDto);
        return "updateProfile";
    }

    @PostMapping("update")
    public String updateProfile(
            @ModelAttribute("updateUser") @Valid UpdateUserDto updateUserDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateProfile";
        }
        profileService.updateProfile(updateUserDto);
        return "redirect:/users/profile";
    }

    @PostMapping(
            value = "update/image",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateImage(
            @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Optional<String> mimeType = Optional.ofNullable(file.getContentType());
        try {
            imageValidator.imageTypeCheck(mimeType);
            imageValidator.imageSizeCheck(file);
            imageService.uploadImage(file);
        } catch (ImageUploadException ex) {
            redirectAttributes.addFlashAttribute(MESSAGE, ex.getMessage());
            return "redirect:/users/profile/update?fail";
        }
        redirectAttributes.addFlashAttribute(
                MESSAGE, "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/users/profile/update?success";
    }

    @ResponseBody
    @GetMapping("/image/{imageName}")
    public byte[] downloadImage(@PathVariable UUID imageName) {
        return imageService.downloadImage(imageName.toString());
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
            redirectAttributes.addFlashAttribute(MESSAGE, "Passwords do not match!");
            return "redirect:/users/profile/password?fail";
        }
        profileService.changePassword(changePasswordDto);
        SecurityContextHolder.getContext()
                .setAuthentication(SecurityUtil.createAnonymousPrincipal());
        redirectAttributes.addFlashAttribute(
                MESSAGE, "Password change successfully! Please login with your new password!");
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
                        MESSAGE, "Profile delete successfully! Please register your new account!");
                return "redirect:/registration?success";
            }
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "Don't joke, this is not your email!");
        return "redirect:/users/profile/delete?fail";
    }
}
