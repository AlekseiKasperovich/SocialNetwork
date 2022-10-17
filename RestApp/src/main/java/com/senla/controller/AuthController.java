package com.senla.controller;

import com.senla.client.AuthRestClient;
import com.senla.dto.profile.EmailDto;
import com.senla.dto.token.TokenDto;
import com.senla.dto.user.*;
import com.senla.security.JwtTokenProvider;
import com.senla.security.UserDetailsImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/auth",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthRestClient authRestClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     * @param createUserDto user email and password
     * @return user
     */
    @PostMapping("registration")
    public DtoUser createUser(@RequestBody @Valid DtoCreateUser createUserDto) {
        return authRestClient.registerNewUserAccount(createUserDto);
    }

    /**
     * @param loginUserDto user email and password
     * @return token
     */
    @PostMapping("login")
    public TokenDto createAuthenticationToken(@RequestBody @Valid LoginUserDto loginUserDto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUserDto.getEmail(), loginUserDto.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SimpleGrantedAuthority authority =
                (SimpleGrantedAuthority) userDetails.getAuthorities().stream().findFirst().get();
        String role = authority.getAuthority();
        return jwtTokenProvider.generateToken(
                userDetails.getUsername(), role, userDetails.getId().toString());
    }

    /**
     * @param emailDto user email
     * @return sent new password to email
     */
    @PostMapping("password/generate")
    public ResponseEntity<Object> generatePassword(@RequestBody @Valid ForgotPasswordDto emailDto) {
        authRestClient.generateNewPassword(emailDto);
        return ResponseEntity.ok("Your new password has been successfully sent!");
    }

    @PostMapping("password/reset")
    public ResponseEntity<Object> resetPassword(@RequestBody @Valid ForgotPasswordDto emailDto) {
        authRestClient.resetPassword(emailDto);
        return ResponseEntity.ok("Check your email!");
    }

    @PostMapping("password/reset/validate")
    public EmailDto validateToken(@RequestBody TokenDto tokenDto) {
        String token = tokenDto.getToken();
        if (jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getUsernameFromToken(token);
            return new EmailDto(email);
        }
        return null;
    }

    @PatchMapping("password/reset/change")
    public ResponseEntity<Object> changePassword(
            @Valid @RequestBody ResetPasswordDto resetPasswordDto) {
        authRestClient.changePassword(resetPasswordDto);
        return ResponseEntity.ok("Password has been successfully change!");
    }
}
