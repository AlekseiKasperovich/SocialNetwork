package com.senla.client.impl;

import com.senla.client.HttpHeaderBuilder;
import com.senla.client.UserRestClient;
import com.senla.dto.user.DtoUser;
import com.senla.property.RequestProperty;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
public class UserRestClientImpl implements UserRestClient {

    private static final String URL = "/api/users/";

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public DtoUser getUserById(UUID id) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + id,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        DtoUser.class)
                .getBody();
    }

    @Override
    public Page<DtoUser> searchUsers(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL;
        } else {
            url = requestProperty.getHost() + URL + requestProperty.getQuestion() + requestParam;
        }
        return restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        new ParameterizedTypeReference<RestResponsePage<DtoUser>>() {})
                .getBody();
    }
}
