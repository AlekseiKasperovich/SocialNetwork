package com.senla.controller;

import com.senla.api.dto.user.DtoCreateUser;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.user.ForgotPasswordDto;
import com.senla.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/auth",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * @param createUserDto user email and password
     * @return user
     */
    @PostMapping("registration")
    public DtoUser createUser(@RequestBody DtoCreateUser createUserDto) {
        return authService.registerNewUserAccount(createUserDto);
    }

    /**
     * @param emailDto user email
     */
    @PostMapping("password/new")
    public void sendPassword(@RequestBody ForgotPasswordDto emailDto) {
        authService.sendNewPassword(emailDto);
    }
}
