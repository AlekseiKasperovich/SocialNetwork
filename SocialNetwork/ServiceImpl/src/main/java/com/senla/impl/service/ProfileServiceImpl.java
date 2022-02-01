package com.senla.impl.service;

import com.senla.api.dto.profile.ChangePasswordDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.service.ProfileService;
import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.User;
import com.senla.impl.model.enums.Status;
import com.senla.impl.service.custom.CustomUserService;
import com.senla.impl.service.custom.PasswordService;
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
    private final PasswordService passwordService;
    private final Mapper mapper;

    /**
     *
     * @return user profile
     */
    @Override
    public DtoUser getUserProfile() {
        return mapper.map(userService.getCurrentUser(), DtoUser.class);
    }

    /**
     *
     * @param updateUserDto user information
     * @return updated user profile
     */
    @Override    
    public DtoUser updateUser(UpdateUserDto updateUserDto) {
        User user = userService.getCurrentUser();
        mapper.map(updateUserDto, user);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /**
     *
     * @return updated user profile whith status = deleted
     */
    @Override
     public DtoUser deleteUser() {
        User user = userService.getCurrentUser();
        user.setStatus(Status.DELETED);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /**
     *
     * @param changePasswordDto new password
     * @return updated user profile
     */
    @Override
        public DtoUser changePassword(ChangePasswordDto changePasswordDto) {
        User user = userService.getCurrentUser();
        user.setPassword(passwordService.encode(changePasswordDto.getPassword()));
        return mapper.map(userService.save(user), DtoUser.class);
    }
}
