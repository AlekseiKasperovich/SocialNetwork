package com.senla.service;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface ProfileService {

    DtoUser getUserProfile(Long id);

    DtoUser updateUser(UpdateUserDto updateUserDto, Long id);

    DtoUser changePassword(ChangePasswordDto changePasswordDto, Long id);

    DtoUser deleteUser(Long id);
}
