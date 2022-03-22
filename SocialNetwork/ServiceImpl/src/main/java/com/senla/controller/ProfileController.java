package com.senla.controller;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.сonstants.Constants;
import com.senla.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/users/profile",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     *
     * @param email
     * @return user profile
     */
    @GetMapping
    public DtoUser getCurrentUserProfile(@RequestHeader(Constants.EMAIL_HEADER) String email) {
        return profileService.getUserProfile(email);
    }

    /**
     *
     * @param updateUserDto user information
     * @param email
     * @return updated user profile
     */
    @PutMapping
    public DtoUser updateProfile(@RequestBody UpdateUserDto updateUserDto,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return profileService.updateUser(updateUserDto, email);
    }

    /**
     *
     * @param changePasswordDto new password
     * @param email
     * @return updated user profile
     */
    @PatchMapping
    public DtoUser changePassword(@RequestBody ChangePasswordDto changePasswordDto,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return profileService.changePassword(changePasswordDto, email);
    }

    /**
     *
     * @param email
     * @return updated user profile whith status = deleted
     */
    @DeleteMapping
    public DtoUser deleteProfile(@RequestHeader(Constants.EMAIL_HEADER) String email) {
        return profileService.deleteUser(email);
    }
}
