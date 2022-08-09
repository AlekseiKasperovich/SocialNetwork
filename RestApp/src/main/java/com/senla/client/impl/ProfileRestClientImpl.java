package com.senla.client.impl;

import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;
import com.senla.client.HttpHeaderBuilder;
import com.senla.client.ProfileRestClient;
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
public class ProfileRestClientImpl implements ProfileRestClient {

    private static final String URL = "/api/users/profile";
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public DtoUser getUserProfile() {
        return restTemplate.exchange(requestProperty.getHost() + URL,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

    @Override
    public DtoUser updateUser(UpdateUserDto updateUserDto) {
        return restTemplate.exchange(requestProperty.getHost() + URL,
                HttpMethod.PUT, new HttpEntity<>(updateUserDto, httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

    @Override
    public DtoUser changePassword(ChangePasswordDto changePasswordDto) {
        String password = changePasswordDto.getPassword();
        String hashPassword = bCryptPasswordEncoder.encode(password);
        changePasswordDto.setPassword(hashPassword);
        changePasswordDto.setMatchingPassword(hashPassword);
        return restTemplate.exchange(requestProperty.getHost() + URL,
                HttpMethod.PATCH, new HttpEntity<>(changePasswordDto, httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

    @Override
    public DtoUser deleteUser() {
        return restTemplate.exchange(requestProperty.getHost() + URL,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

}
