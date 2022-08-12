package com.senla.client;

import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;

/** @author Aliaksei Kaspiarovich */
public interface ProfileRestClient {

    DtoUser getUserProfile();

    DtoUser updateUser(UpdateUserDto updateUserDto);

    DtoUser changePassword(ChangePasswordDto changePasswordDto);

    DtoUser deleteUser();
}
