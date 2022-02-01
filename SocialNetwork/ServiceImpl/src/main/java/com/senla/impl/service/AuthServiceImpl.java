package com.senla.impl.service;

import com.senla.impl.service.custom.EmailService;
import com.senla.impl.service.custom.PasswordService;
import com.senla.api.dto.user.CreateUser;
import com.senla.api.dto.user.ForgotPasswordDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.service.AuthService;

import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.User;
import com.senla.impl.model.enums.Roles;
import com.senla.impl.model.enums.Status;
import com.senla.impl.service.custom.CustomUserService;
import com.senla.impl.service.custom.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
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
     *
     * @param createUserDto user email and password
     * @return user
     */
    @Override
    public DtoUser registerNewUserAccount(CreateUser createUserDto) {
        userService.existsByEmail(createUserDto.getEmail());
        User user = User.builder()
                .email(createUserDto.getEmail())
                .password(passwordService.encode(createUserDto.getPassword()))
                .role(roleService.findByName(Roles.ROLE_USER))
                .status(Status.ACTIVE)
                .build();
        return mapper.map(userService.save(user), DtoUser.class);
    }

    /**
     *
     * @param emailDto user email
     */
    @Override
    public void sendNewPassword(ForgotPasswordDto emailDto) {
        User user = userService.findUserByEmail(emailDto.getEmail());
        String newPassword = passwordService.generatePassword();
        emailService.sendMessage(user.getEmail(), emailBody,
                emailBody + " " + newPassword);
        user.setPassword(passwordService.encode(newPassword));
        userService.save(user);
    }

}
