package com.senla.client.impl;

import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.сonstants.Constants;
import com.senla.client.AdminUserRestClient;
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
public class AdminUserRestClientImpl implements AdminUserRestClient {

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private static final String URL = "/api/admin/users/";
    private static final String BLOCK = "/block";
    private static final String UNBLOCK = "/unblock";

    @Override
    public DtoUser blockUser(Long id) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + id + BLOCK,
                HttpMethod.PATCH,
                new HttpEntity<>(httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

    @Override
    public DtoUser unblockUser(Long id) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + id + UNBLOCK,
                HttpMethod.PATCH, new HttpEntity<>(httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

}
