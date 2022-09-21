package com.senla.client.impl;

import com.senla.client.AuthRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ForgotPasswordDto;
import com.senla.property.RequestProperty;
import com.senla.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class AuthRestClientImpl implements AuthRestClient {

    private static final String URL = "/api/auth/";
    private static final String REGISTRATION = "registration";
    private static final String PASSWORD_GENERATE = "password/generate";
    private static final String PASSWORD_RESET = "password/reset";
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public DtoUser registerNewUserAccount(DtoCreateUser createUserDto) {
        String password = createUserDto.getPassword();
        String hashPassword = bCryptPasswordEncoder.encode(password);
        createUserDto.setPassword(hashPassword);
        createUserDto.setMatchingPassword(hashPassword);
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + REGISTRATION,
                        HttpMethod.POST,
                        new HttpEntity<>(
                                createUserDto, httpHeaderBuilder.build(createUserDto.getEmail())),
                        DtoUser.class)
                .getBody();
    }

    @Override
    public void generateNewPassword(ForgotPasswordDto emailDto) {
        restTemplate.exchange(
                requestProperty.getHost() + URL + PASSWORD_GENERATE,
                HttpMethod.POST,
                new HttpEntity<>(null, httpHeaderBuilder.build(emailDto.getEmail())),
                Void.class);
    }

    @Override
    public void resetPassword(ForgotPasswordDto emailDto) {
        String email = emailDto.getEmail();
        restTemplate.exchange(
                requestProperty.getHost() + URL + PASSWORD_RESET,
                HttpMethod.POST,
                new HttpEntity<>(null, httpHeaderBuilder.build(email, jwtTokenProvider.generateToken(email))),
                Void.class);
    }
}
