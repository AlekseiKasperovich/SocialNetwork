package com.senla.service;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface ProfileService {

    DtoUser getUserProfile(String email);

    DtoUser updateUser(UpdateUserDto updateUserDto, String email);

    DtoUser changePassword(ChangePasswordDto changePasswordDto, String email);

    DtoUser deleteUser(String email);
}
