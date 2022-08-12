package com.senla.service;

import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;

/** @author Aliaksei Kaspiarovich */
public interface ProfileService {

    DtoUser getUserProfile(Long id);

    DtoUser updateUser(UpdateUserDto updateUserDto, Long id);

    DtoUser changePassword(ChangePasswordDto changePasswordDto, Long id);

    DtoUser deleteUser(Long id);
}
