package com.senla.web.service;

import com.senla.web.dto.profile.ChangePasswordDto;
import com.senla.web.dto.profile.UpdateUserDto;
import com.senla.web.dto.user.DtoUser;

public interface ProfileService {

    DtoUser getCurrentUserProfile();

    DtoUser updateUserProfile(UpdateUserDto updateUserDto);

    DtoUser changePassword(ChangePasswordDto changePasswordDto);

    void deleteProfile();
}
