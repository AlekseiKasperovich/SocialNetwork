package com.senla.service;

import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.ImageDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface ProfileService {

    DtoUser getUserProfile(UUID id);

    DtoUser updateUser(UpdateUserDto updateUserDto, UUID id);

    DtoUser changePassword(ChangePasswordDto changePasswordDto, UUID id);

    DtoUser deleteUser(UUID id);

    void updateImage(ImageDto imageDto, UUID id);
}
