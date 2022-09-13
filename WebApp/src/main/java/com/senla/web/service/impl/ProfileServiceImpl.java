package com.senla.web.service.impl;

import com.senla.web.dto.profile.ChangePasswordDto;
import com.senla.web.dto.profile.UpdateUserDto;
import com.senla.web.dto.user.DtoUser;
import com.senla.web.feign.ProfileClient;
import com.senla.web.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileClient profileClient;

    @Override
    public DtoUser getCurrentUserProfile() {
        return profileClient.getProfile().getBody();
    }

    @Override
    public DtoUser updateUserProfile(UpdateUserDto updateUserDto) {
        return profileClient.updateProfile(updateUserDto).getBody();
    }

    @Override
    public DtoUser changePassword(ChangePasswordDto changePasswordDto) {
        return profileClient.changePassword(changePasswordDto).getBody();
    }

    @Override
    public void deleteProfile() {
        profileClient.deleteProfile();
    }
}
