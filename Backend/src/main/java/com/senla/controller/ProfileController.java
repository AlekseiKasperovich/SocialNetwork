package com.senla.controller;

import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;
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

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/users/profile",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     * @param id id
     * @return user profile
     */
    @GetMapping
    public DtoUser getCurrentUserProfile(@RequestHeader("${request.id}") Long id) {
        return profileService.getUserProfile(id);
    }

    /**
     * @param updateUserDto user information
     * @param id id
     * @return updated user profile
     */
    @PutMapping
    public DtoUser updateProfile(
            @RequestBody UpdateUserDto updateUserDto, @RequestHeader("${request.id}") Long id) {
        return profileService.updateUser(updateUserDto, id);
    }

    /**
     * @param changePasswordDto new password
     * @param id id
     * @return updated user profile
     */
    @PatchMapping
    public DtoUser changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @RequestHeader("${request.id}") Long id) {
        return profileService.changePassword(changePasswordDto, id);
    }

    /**
     * @param id id
     * @return updated user profile with status = deleted
     */
    @DeleteMapping
    public DtoUser deleteProfile(@RequestHeader("${request.id}") Long id) {
        return profileService.deleteUser(id);
    }
}
