package com.senla.service.impl;

import com.senla.dto.constants.Status;
import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.ImageDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;
import com.senla.mapper.Mapper;
import com.senla.model.User;
import com.senla.service.CustomUserService;
import com.senla.service.ProfileService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final CustomUserService userService;
    private final Mapper mapper;

    /**
     * @param id id
     * @return user profile
     */
    @Override
    public DtoUser getUserProfile(UUID id) {
        return mapper.map(userService.findUserById(id), DtoUser.class);
    }

    /**
     * @param updateUserDto user information
     * @param id id
     * @return updated user profile
     */
    @Override
    public DtoUser updateUser(UpdateUserDto updateUserDto, UUID id) {
        User user = userService.findUserById(id);
        mapper.map(updateUserDto, user);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /**
     * @param id id
     * @return updated user profile with status = deleted
     */
    @Override
    public DtoUser deleteUser(UUID id) {
        User user = userService.findUserById(id);
        user.setStatus(Status.DELETED);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    @Override
    public void updateImage(ImageDto imageDto, UUID id) {
        User user = userService.findUserById(id);
        user.setImage(UUID.fromString(imageDto.getImage()));
        userService.save(user);
    }

    /**
     * @param changePasswordDto new password
     * @param id id
     * @return updated user profile
     */
    @Override
    public DtoUser changePassword(ChangePasswordDto changePasswordDto, UUID id) {
        User user = userService.findUserById(id);
        user.setPassword(changePasswordDto.getPassword());
        return mapper.map(userService.save(user), DtoUser.class);
    }
}
