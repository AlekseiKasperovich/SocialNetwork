package com.senla.service.impl;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.service.ProfileService;
import com.senla.mapper.Mapper;
import com.senla.model.User;
import com.senla.api.dto.сonstants.Status;
import com.senla.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final CustomUserService userService;
    private final Mapper mapper;

    /**
     *
     * @param email
     * @return user profile
     */
    @Override
    public DtoUser getUserProfile(String email) {
        return mapper.map(userService.findUserByEmail(email), DtoUser.class);
    }

    /**
     *
     * @param updateUserDto user information
     * @param email
     * @return updated user profile
     */
    @Override
    public DtoUser updateUser(UpdateUserDto updateUserDto, String email) {
        User user = userService.findUserByEmail(email);
        mapper.map(updateUserDto, user);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /**
     *
     * @param email
     * @return updated user profile whith status = deleted
     */
    @Override
    public DtoUser deleteUser(String email) {
        User user = userService.findUserByEmail(email);
        user.setStatus(Status.DELETED);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /**
     *
     * @param changePasswordDto new password
     * @param email
     * @return updated user profile
     */
    @Override
    public DtoUser changePassword(ChangePasswordDto changePasswordDto, String email) {
        User user = userService.findUserByEmail(email);
        user.setPassword(changePasswordDto.getPassword());
        return mapper.map(userService.save(user), DtoUser.class);
    }
}
