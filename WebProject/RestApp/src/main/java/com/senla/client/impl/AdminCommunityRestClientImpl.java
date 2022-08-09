package com.senla.client.impl;

import com.senla.dto.community.CommunityDto;
import com.senla.dto.community.CreateCommunityDto;
import com.senla.client.AdminCommunityRestClient;
import com.senla.client.HttpHeaderBuilder;
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
public class AdminCommunityRestClientImpl implements AdminCommunityRestClient {

    private static final String URL = "/api/admin/communities/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public CommunityDto createCommunity(CreateCommunityDto createCommunityDto) {
        return restTemplate.exchange(requestProperty.getHost() + URL,
                HttpMethod.POST,
                new HttpEntity<>(createCommunityDto, httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override
    public CommunityDto updateCommunity(Long id, CreateCommunityDto createCommunityDto) {
        return restTemplate.exchange(requestProperty.getHost() + URL + id,
                HttpMethod.PUT,
                new HttpEntity<>(createCommunityDto, httpHeaderBuilder.build()),
                CommunityDto.class).getBody();
    }

    @Override
    public void deleteCommunity(Long id) {
        restTemplate.exchange(requestProperty.getHost() + URL + id,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

}
