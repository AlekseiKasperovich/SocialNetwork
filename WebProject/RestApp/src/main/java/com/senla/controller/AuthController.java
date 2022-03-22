package com.senla.controller;

import com.senla.api.dto.user.DtoCreateUser;
import com.senla.api.dto.user.ForgotPasswordDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.client.AuthRestClient;
import com.senla.security.JwtTokenProvider;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/auth",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthRestClient authRestClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     *
     * @param createUserDto user email and password
     * @return user
     */
    @PostMapping("registration")
    public DtoUser createUser(@RequestBody @Valid DtoCreateUser createUserDto) {
        return authRestClient.registerNewUserAccount(createUserDto);
    }

    /**
     *
     * @param createUserDto user email and password
     * @return token
     */
    @PostMapping("login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody @Valid DtoCreateUser createUserDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                createUserDto.getEmail(), createUserDto.getPassword()));
        String token = jwtTokenProvider.generateToken(createUserDto.getEmail());
        return ResponseEntity.ok("Token: "+token);
    }

    /**
     *
     * @param emailDto user email
     * @return sent new password to email
     */
    @PostMapping("password/new")
    public ResponseEntity<?> sendPassword(@RequestBody @Valid ForgotPasswordDto emailDto) {
        authRestClient.sendNewPassword(emailDto);
        return ResponseEntity.ok("Your new password has been successfully sent!");
    }
}
