package com.senla.client.impl;

import com.senla.dto.community.CommunityMessageDto;
import com.senla.dto.message.CreateMessageDto;
import com.senla.client.CommunityMessageRestClient;
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
public class CommunityMessageRestClientImpl implements CommunityMessageRestClient {

    private static final String URL = "/api/communities/";
    private static final String MESSAGES = "/messages/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public CommunityMessageDto getCommunityMessageById(Long communityId, Long messageId) {
        return restTemplate.exchange(requestProperty.getHost() + URL + communityId + MESSAGES + messageId,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                CommunityMessageDto.class).getBody();
    }

    @Override
    public CommunityMessageDto createCommunityMessage(Long communityId,
                                                      CreateMessageDto createMessageDto) {
        return restTemplate.exchange(requestProperty.getHost() + URL + communityId + MESSAGES,
                HttpMethod.POST,
                new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                CommunityMessageDto.class).getBody();
    }

    @Override
    public CommunityMessageDto updateCommunityMessage(Long communityId, Long messageId,
                                                      CreateMessageDto createMessageDto) {
        return restTemplate.exchange(requestProperty.getHost() + URL + communityId + MESSAGES + messageId,
                HttpMethod.PUT,
                new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                CommunityMessageDto.class).getBody();
    }

    @Override
    public void deleteCommunityMessage(Long communityId, Long messageId) {
        restTemplate.exchange(requestProperty.getHost() + URL + communityId + MESSAGES + messageId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public Page<CommunityMessageDto> findAll(Long communityId, Pageable pageable,
                                             HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL + communityId;
        } else {
            url = requestProperty.getHost() + URL + communityId + requestProperty.getQuestion() + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<CommunityMessageDto>>() {
                }).getBody();
    }

}
