package com.senla.controller;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
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
     * @param email
     * @return user profile
     */
    @GetMapping
    public DtoUser getCurrentUserProfile(@RequestHeader("${request.email}") String email) {
        return profileService.getUserProfile(email);
    }

    /**
     * @param updateUserDto user information
     * @param email
     * @return updated user profile
     */
    @PutMapping
    public DtoUser updateProfile(@RequestBody UpdateUserDto updateUserDto,
                                 @RequestHeader("${request.email}") String email) {
        return profileService.updateUser(updateUserDto, email);
    }

    /**
     * @param changePasswordDto new password
     * @param email
     * @return updated user profile
     */
    @PatchMapping
    public DtoUser changePassword(@RequestBody ChangePasswordDto changePasswordDto,
                                  @RequestHeader("${request.email}") String email) {
        return profileService.changePassword(changePasswordDto, email);
    }

    /**
     * @param email
     * @return updated user profile whith status = deleted
     */
    @DeleteMapping
    public DtoUser deleteProfile(@RequestHeader("${request.email}") String email) {
        return profileService.deleteUser(email);
    }
}
