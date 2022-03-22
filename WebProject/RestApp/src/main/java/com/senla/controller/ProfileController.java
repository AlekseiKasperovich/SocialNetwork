package com.senla.controller;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.client.ProfileRestClient;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/users/profile",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileRestClient profileRestClient;

    /**
     *
     * @return user profile
     */
    @GetMapping
    public DtoUser getCurrentUserProfile() {
        return profileRestClient.getUserProfile();
    }

    /**
     *
     * @param updateUserDto user information
     * @return updated user profile
     */
    @PutMapping
    public DtoUser updateProfile(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return profileRestClient.updateUser(updateUserDto);
    }

    /**
     *
     * @param changePasswordDto new password
     * @return updated user profile
     */
    @PatchMapping
    public DtoUser changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return profileRestClient.changePassword(changePasswordDto);
    }

    /**
     *
     * @return updated user profile whith status = deleted
     */
    @DeleteMapping
    public DtoUser deleteProfile() {
        return profileRestClient.deleteUser();
    }
}
