package com.senla.service.impl;

import com.senla.dto.constants.Roles;
import com.senla.dto.constants.Status;
import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ResetPasswordDto;
import com.senla.mapper.Mapper;
import com.senla.model.User;
import com.senla.service.AuthService;
import com.senla.service.CustomUserService;
import com.senla.service.EmailService;
import com.senla.service.PasswordService;
import com.senla.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final CustomUserService userService;
    private final RoleService roleService;
    private final Mapper mapper;
    private final PasswordService passwordService;
    private final EmailService emailService;

    @Value("${email.body}")
    private String emailBody;

    /**
     * @param createUserDto user email and password
     * @return user
     */
    @Override
    public DtoUser registerNewUserAccount(DtoCreateUser createUserDto) {
        userService.existsByEmail(createUserDto.getEmail());
        User user =
                User.builder()
                        .email(createUserDto.getEmail())
                        .password(createUserDto.getPassword())
                        .role(roleService.findByName(Roles.ROLE_USER))
                        .status(Status.ACTIVE)
                        .build();
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /** @param email user email */
    @Override
    public void generatePassword(String email) {
        User user = userService.findUserByEmail(email);
        String newPassword = passwordService.generatePassword();
        emailService.sendMessage(user.getEmail(), emailBody, emailBody + " " + newPassword);
        user.setPassword(passwordService.encode(newPassword));
        userService.save(user);
    }

    @Override
    public void resetPassword(String email, String token) {
        User user = userService.findUserByEmail(email);
        emailService.sendMessage(
                user.getEmail(),
                emailBody,
                "If you would like to change your password, please follow this link: "
                        + "http://localhost:8082/password/reset/"
                        + token
                        + " Please note that this link expires 24 hours from when this email was"
                        + " delivered, so be sure to reset your password immediately.");
    }

    @Override
    public void changePassword(ResetPasswordDto resetPasswordDto) {
        User user = userService.findUserByEmail(resetPasswordDto.getEmail());
        user.setPassword(resetPasswordDto.getPassword());
        userService.save(user);
    }
}
