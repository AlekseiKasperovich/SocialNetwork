package com.senla.client.impl;

import com.senla.client.FriendshipRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.dto.friendship.FriendshipDto;
import com.senla.exception.MyAccessDeniedException;
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
public class FriendshipRestClientImpl extends CurrentUserService implements FriendshipRestClient {

    private static final String URL = "/api/friendships/";
    private static final String FRIEND = "?friendId=";
    private static final String REQUESTS = "requests";
    private static final String OUTGOING = "/outgoing";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public FriendshipDto getFriendshipById(UUID friendshipId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + friendshipId,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        FriendshipDto.class)
                .getBody();
    }

    @Override
    public FriendshipDto createFriendship(UUID friendId) {
        if (!friendId.equals(getCurrentUserId())) {
            return restTemplate
                    .exchange(
                            requestProperty.getHost() + URL + FRIEND + friendId,
                            HttpMethod.POST,
                            new HttpEntity<>(httpHeaderBuilder.build()),
                            FriendshipDto.class)
                    .getBody();
        } else {
            throw new MyAccessDeniedException("You cannot send a request to yourself");
        }
    }

    @Override
    public FriendshipDto acceptFriendship(UUID friendshipId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + friendshipId,
                        HttpMethod.PUT,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        FriendshipDto.class)
                .getBody();
    }

    @Override
    public void deleteFriendship(UUID friendshipId) {
        restTemplate.exchange(
                requestProperty.getHost() + URL + friendshipId,
                HttpMethod.DELETE,
                new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public Page<FriendshipDto> findMyFriendshipRequests(
            Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL + REQUESTS;
        } else {
            url =
                    requestProperty.getHost()
                            + URL
                            + REQUESTS
                            + requestProperty.getQuestion()
                            + requestParam;
        }
        return restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        new ParameterizedTypeReference<RestResponsePage<FriendshipDto>>() {})
                .getBody();
    }

    @Override
    public Page<FriendshipDto> findMyFriends(Pageable pageable, HttpServletRequest request) {
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
                        new ParameterizedTypeReference<RestResponsePage<FriendshipDto>>() {})
                .getBody();
    }

    @Override
    public Page<FriendshipDto> findOutgoingFriendshipRequests(
            Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL + REQUESTS + OUTGOING;
        } else {
            url =
                    requestProperty.getHost()
                            + URL
                            + REQUESTS
                            + OUTGOING
                            + requestProperty.getQuestion()
                            + requestParam;
        }
        return restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        new ParameterizedTypeReference<RestResponsePage<FriendshipDto>>() {})
                .getBody();
    }
}
