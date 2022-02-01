package com.senla.rest.controller;

import com.senla.api.dto.user.CreateUser;
import com.senla.api.dto.user.ForgotPasswordDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.service.AuthService;
import com.senla.rest.security.JwtTokenProvider;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     *
     * @param createUserDto user email and password
     * @return user
     */
    @PostMapping("registration")
    public DtoUser createUser(@RequestBody @Valid CreateUser createUserDto) {
        return authService.registerNewUserAccount(createUserDto);
    }

    /**
     *
     * @param createUserDto user email and password
     * @return token
     */
    @PostMapping("login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody @Valid CreateUser createUserDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                createUserDto.getEmail(), createUserDto.getPassword()));
        String token = jwtTokenProvider.generateToken(createUserDto.getEmail());
        return ResponseEntity.ok(token);
    }

    /**
     *
     * @param emailDto user email
     * @return sent new password to email
     */
    @PostMapping("password/new")
    public ResponseEntity<?> sendPassword(@RequestBody @Valid ForgotPasswordDto emailDto) {
        authService.sendNewPassword(emailDto);
        return ResponseEntity.ok("Your new password has been successfully sent!");
    }
}
