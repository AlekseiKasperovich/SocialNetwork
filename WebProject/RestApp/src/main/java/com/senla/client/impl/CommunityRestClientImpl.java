package com.senla.client.impl;

import com.senla.api.dto.community.CommunityDto;
import com.senla.client.CommunityRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.property.RequestProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class CommunityRestClientImpl implements CommunityRestClient {

    private static final String URL = "/api/communities/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public CommunityDto getCommunityById(Long communityId) {
        return restTemplate.exchange(requestProperty.getHost() + URL + communityId,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override
    public CommunityDto addUser(Long communityId) {
        return restTemplate.exchange(requestProperty.getHost() + URL + communityId,
                HttpMethod.PUT, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override

    public CommunityDto deleteUser(Long communityId) {
        return restTemplate.exchange(requestProperty.getHost() + URL + communityId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override
    public Page<CommunityDto> findAll(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL;
        } else {
            url = requestProperty.getHost() + URL + requestProperty.getQuestion() + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<CommunityDto>>() {
                }).getBody();
    }

}
