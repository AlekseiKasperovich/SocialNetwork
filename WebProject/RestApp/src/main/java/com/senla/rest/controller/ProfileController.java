package com.senla.rest.controller;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.service.ProfileService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/users/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     *
     * @return user profile
     */
    @GetMapping
    public DtoUser getCurrentUserProfile() {
        return profileService.getUserProfile();
    }

    /**
     *
     * @param updateUserDto user information
     * @return updated user profile
     */
    @PutMapping
    public DtoUser updateProfile(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return profileService.updateUser(updateUserDto);
    }

    /**
     *
     * @param changePasswordDto new password
     * @return updated user profile
     */
    @PatchMapping
    public DtoUser changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return profileService.changePassword(changePasswordDto);
    }

    /**
     *
     * @return updated user profile whith status = deleted
     */
    @DeleteMapping
    public DtoUser deleteProfile() {
        return profileService.deleteUser();
    }
}
