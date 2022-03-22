package com.senla.client.impl;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.сonstants.Constants;
import com.senla.client.CommunityRestClient;
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
public class CommunityRestClientImpl implements CommunityRestClient {

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private static final String URL = "/api/communities/";

    @Override
    public CommunityDto getCommunityById(Long communityId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + communityId,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override
    public CommunityDto addUser(Long communityId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + communityId,
                HttpMethod.PUT, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override

    public CommunityDto deleteUser(Long communityId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + communityId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override
    public Page<CommunityDto> findAll(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = Constants.HOST_PORT + URL;
        } else {
            url = Constants.HOST_PORT + URL + Constants.QUESTION + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<CommunityDto>>() {
        }).getBody();
    }

}
