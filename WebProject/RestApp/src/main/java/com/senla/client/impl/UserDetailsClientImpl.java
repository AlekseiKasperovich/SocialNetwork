package com.senla.client.impl;

import com.senla.api.dto.user.UserDetailsDto;
import com.senla.client.UserDetailsClient;
import com.senla.api.dto.сonstants.Constants;
import com.senla.client.HttpHeaderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class UserDetailsClientImpl implements UserDetailsClient {

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private static final String URL = "/api/users/details";

    @Override
    public UserDetailsDto findByEmail(String email) {
        return restTemplate.exchange(Constants.HOST_PORT + URL,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build(email)),
                UserDetailsDto.class).getBody();
    }

}
