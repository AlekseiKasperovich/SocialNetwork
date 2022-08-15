package com.senla.client.impl;

import com.senla.client.HttpHeaderBuilder;
import com.senla.client.MessageRestClient;
import com.senla.dto.message.CreateMessageDto;
import com.senla.dto.message.MessageDto;
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
public class MessageRestClientImpl implements MessageRestClient {

    private static final String URL = "/api/messages/";
    private static final String RECEIVER = "?receiverId=";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public MessageDto getMessageById(UUID messageId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + messageId,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        MessageDto.class)
                .getBody();
    }

    @Override
    public MessageDto createMessage(UUID receiverId, CreateMessageDto createMessageDto) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + RECEIVER + receiverId,
                        HttpMethod.POST,
                        new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                        MessageDto.class)
                .getBody();
    }

    @Override
    public MessageDto updateMessage(UUID messageId, CreateMessageDto createMessageDto) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + messageId,
                        HttpMethod.PUT,
                        new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                        MessageDto.class)
                .getBody();
    }

    @Override
    public void deleteMessage(UUID messageId) {
        restTemplate.exchange(
                requestProperty.getHost() + URL + messageId,
                HttpMethod.DELETE,
                new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public Page<MessageDto> findAll(
            UUID receiverId, Pageable pageable, HttpServletRequest request) {
        return restTemplate
                .exchange(
                        requestProperty.getHost()
                                + URL
                                + requestProperty.getQuestion()
                                + request.getQueryString(),
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        new ParameterizedTypeReference<RestResponsePage<MessageDto>>() {})
                .getBody();
    }
}
