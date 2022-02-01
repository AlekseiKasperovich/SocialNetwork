package com.senla.api.service;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface ProfileService {

    DtoUser getUserProfile();

    DtoUser updateUser( UpdateUserDto updateUserDto);

    DtoUser changePassword(ChangePasswordDto changePasswordDto);

    DtoUser deleteUser();
}
