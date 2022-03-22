package com.senla.client.impl;

import com.senla.api.dto.user.DtoUser;
import com.senla.client.UserRestClient;
import com.senla.api.dto.сonstants.Constants;
import com.senla.client.HttpHeaderBuilder;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class UserRestClientImpl implements UserRestClient {

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private static final String URL = "/api/users/";

    @Override
    public DtoUser getUserById(Long id) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + id,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                DtoUser.class).getBody();
    }

    @Override
    public Page<DtoUser> searchUsers(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = Constants.HOST_PORT + URL;
        } else {
            url = Constants.HOST_PORT + URL + Constants.QUESTION + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<DtoUser>>() {
        }).getBody();
    }

}
