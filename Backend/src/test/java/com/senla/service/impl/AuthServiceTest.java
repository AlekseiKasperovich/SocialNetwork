package com.senla.service.impl;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ForgotPasswordDto;
import com.senla.exception.UserAlreadyExistException;
import com.senla.exception.UserNotFoundException;
import com.senla.model.User;
import com.senla.service.AuthService;
import com.senla.service.CustomUserService;
import com.senla.service.custom.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AuthServiceTest extends AbstractIntegrationTest {

    @Autowired private AuthService authService;

    @Autowired private CustomUserService customUserService;

    @Test
    void givenUser_whenRegister_thenReturnUser() {
        DtoCreateUser dtoCreateUser =
                DtoCreateUser.builder()
                        .email("email@gmail.com")
                        .password("password")
                        .matchingPassword("password")
                        .build();

        DtoUser dtoUser = authService.registerNewUserAccount(dtoCreateUser);

        Assertions.assertNotNull(dtoUser);
        Assertions.assertEquals(dtoCreateUser.getEmail(), dtoUser.getEmail());
    }

    @Test
    void givenExistingEmail_whenRegister_thenTrowException() {
        DtoCreateUser dtoCreateUser =
                DtoCreateUser.builder()
                        .email("user@gmail.com")
                        .password("password")
                        .matchingPassword("password")
                        .build();

        Assertions.assertThrows(
                UserAlreadyExistException.class,
                () -> authService.registerNewUserAccount(dtoCreateUser));
    }

    @Test
    void givenExistingEmail_whenSendPassword_thenSaveNewPassword() {
        String email = "user@gmail.com";
        User beforeUpdatePassword = customUserService.findUserByEmail(email);
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto(email);

        authService.sendNewPassword(forgotPasswordDto);

        User afterUpdatePassword = customUserService.findUserByEmail(email);

        Assertions.assertNotNull(afterUpdatePassword);
        Assertions.assertNotEquals(
                beforeUpdatePassword.getPassword(), afterUpdatePassword.getPassword());
    }

    @Test
    void givenNonExistingEmail_whenSendPassword_thenTrowException() {
        String email = "another@gmail.com";
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto(email);

        Assertions.assertThrows(
                UserNotFoundException.class, () -> authService.sendNewPassword(forgotPasswordDto));
    }
}
