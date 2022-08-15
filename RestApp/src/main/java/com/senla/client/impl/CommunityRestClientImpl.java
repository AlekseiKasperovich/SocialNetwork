package com.senla.client.impl;

import com.senla.client.CommunityRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.dto.community.CommunityDto;
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
public class CommunityRestClientImpl implements CommunityRestClient {

    private static final String URL = "/api/communities/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public CommunityDto getCommunityById(UUID communityId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + communityId,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        CommunityDto.class)
                .getBody();
    }

    @Override
    public CommunityDto addUser(UUID communityId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + communityId,
                        HttpMethod.PUT,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        CommunityDto.class)
                .getBody();
    }

    @Override
    public CommunityDto deleteUser(UUID communityId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + communityId,
                        HttpMethod.DELETE,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        CommunityDto.class)
                .getBody();
    }

    @Override
    public Page<CommunityDto> findAll(Pageable pageable, HttpServletRequest request) {
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
                        new ParameterizedTypeReference<RestResponsePage<CommunityDto>>() {})
                .getBody();
    }
}
