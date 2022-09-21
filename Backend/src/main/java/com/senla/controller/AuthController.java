package com.senla.controller;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/auth",
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

    /** @param email user email */
    @PostMapping("password/generate")
    public void generatePassword(@RequestHeader("${request.email}") String email) {
        authService.generatePassword(email);
    }

    @PostMapping("password/reset")
    public void resetPassword(
            @RequestHeader("${request.email}") String email,
            @RequestHeader("${request.token}") String token) {
        authService.resetPassword(email, token);
    }
}
