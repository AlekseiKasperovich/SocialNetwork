package com.senla.client;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface ProfileRestClient {

    DtoUser getUserProfile();

    DtoUser updateUser(UpdateUserDto updateUserDto);

    DtoUser changePassword(ChangePasswordDto changePasswordDto);

    DtoUser deleteUser();

}
