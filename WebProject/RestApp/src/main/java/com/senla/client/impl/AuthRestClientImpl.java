package com.senla.client.impl;

import com.senla.api.dto.user.DtoCreateUser;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.user.ForgotPasswordDto;
import com.senla.client.AuthRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.property.RequestProperty;
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
    private static final String PASSWORD = "password/new";
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public DtoUser registerNewUserAccount(DtoCreateUser createUserDto) {
        String password = createUserDto.getPassword();
        String hashPassword = bCryptPasswordEncoder.encode(password);
        createUserDto.setPassword(hashPassword);
        createUserDto.setMatchingPassword(hashPassword);
        return restTemplate.exchange(requestProperty.getHost() + URL + REGISTRATION,
                HttpMethod.POST,
                new HttpEntity<>(createUserDto,
                        httpHeaderBuilder.build(createUserDto.getEmail())),
                DtoUser.class).getBody();
    }

    @Override
    public void sendNewPassword(ForgotPasswordDto emailDto) {
        restTemplate.exchange(requestProperty.getHost() + URL + PASSWORD,
                HttpMethod.POST,
                new HttpEntity<>(emailDto, httpHeaderBuilder.build(emailDto.getEmail())),
                Void.class);
    }

}
