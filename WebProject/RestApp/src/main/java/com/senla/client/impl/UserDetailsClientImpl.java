package com.senla.client.impl;

import com.senla.dto.user.UserDetailsDto;
import com.senla.client.HttpHeaderBuilder;
import com.senla.client.UserDetailsClient;
import com.senla.property.RequestProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class UserDetailsClientImpl implements UserDetailsClient {

    private static final String URL = "/api/users/details";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public UserDetailsDto findByEmail(String email) {
        return restTemplate.exchange(requestProperty.getHost() + URL,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build(email)),
                UserDetailsDto.class).getBody();
    }

}
