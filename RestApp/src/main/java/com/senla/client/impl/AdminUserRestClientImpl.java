package com.senla.client.impl;

import com.senla.client.AdminUserRestClient;
import com.senla.client.HttpHeaderBuilder;
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
public class AdminUserRestClientImpl implements AdminUserRestClient {

    private static final String URL = "/api/admin/users/";
    private static final String BANNED = "banned";
    private static final String BLOCK = "/block";
    private static final String UNBLOCK = "/unblock";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public DtoUser blockUser(UUID id) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + id + BLOCK,
                        HttpMethod.PATCH,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        DtoUser.class)
                .getBody();
    }

    @Override
    public DtoUser unblockUser(UUID id) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + id + UNBLOCK,
                        HttpMethod.PATCH,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        DtoUser.class)
                .getBody();
    }

    @Override
    public Page<DtoUser> getBannedUsers(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL + BANNED;
        } else {
            url =
                    requestProperty.getHost()
                            + URL
                            + BANNED
                            + requestProperty.getQuestion()
                            + requestParam;
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
