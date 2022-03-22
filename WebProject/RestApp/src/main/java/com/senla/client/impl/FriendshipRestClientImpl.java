package com.senla.client.impl;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.client.FriendshipRestClient;
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
public class FriendshipRestClientImpl implements FriendshipRestClient {

    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private static final String URL = "/api/friendships/";
    private static final String FRIEND = "?friendId=";
    private static final String REQUESTS = "requests";

    @Override
    public FriendshipDto getFriendshipById(Long friendshipId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + friendshipId,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                FriendshipDto.class).getBody();
    }

    @Override
    public FriendshipDto createFriendship(Long friendId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + FRIEND + friendId,
                HttpMethod.POST, new HttpEntity<>(httpHeaderBuilder.build()),
                FriendshipDto.class).getBody();
    }

    @Override
    public FriendshipDto acceptFriendship(Long friendshipId) {
        return restTemplate.exchange(Constants.HOST_PORT + URL + friendshipId,
                HttpMethod.PUT, new HttpEntity<>(httpHeaderBuilder.build()),
                FriendshipDto.class).getBody();
    }

    @Override
    public void deleteFriendship(Long friendshipId) {
        restTemplate.exchange(Constants.HOST_PORT + URL + friendshipId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public Page<FriendshipDto> findMyFriendshipRequests(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = Constants.HOST_PORT + URL + REQUESTS;
        } else {
            url = Constants.HOST_PORT + URL + REQUESTS + Constants.QUESTION + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<FriendshipDto>>() {
        }).getBody();
    }

    @Override
    public Page<FriendshipDto> findAll(Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = Constants.HOST_PORT + URL;
        } else {
            url = Constants.HOST_PORT + URL + Constants.QUESTION + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<FriendshipDto>>() {
        }).getBody();
    }
}
