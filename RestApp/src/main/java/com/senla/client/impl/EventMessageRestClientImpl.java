package com.senla.client.impl;

import com.senla.client.EventMessageRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.dto.event.EventMessageDto;
import com.senla.dto.message.CreateMessageDto;
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
public class EventMessageRestClientImpl implements EventMessageRestClient {

    private static final String URL = "/api/events/";
    private static final String MESSAGES = "/messages/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public EventMessageDto getEventMessageById(UUID eventId, UUID messageId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId + MESSAGES + messageId,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        EventMessageDto.class)
                .getBody();
    }

    @Override
    public EventMessageDto createEventMessage(UUID eventId, CreateMessageDto createMessageDto) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId,
                        HttpMethod.POST,
                        new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                        EventMessageDto.class)
                .getBody();
    }

    @Override
    public EventMessageDto updateEventMessage(
            UUID eventId, UUID messageId, CreateMessageDto createMessageDto) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId + MESSAGES + messageId,
                        HttpMethod.PUT,
                        new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                        EventMessageDto.class)
                .getBody();
    }

    @Override
    public void deleteEventMessage(UUID eventId, UUID messageId) {
        restTemplate.exchange(
                requestProperty.getHost() + URL + eventId + MESSAGES + messageId,
                HttpMethod.DELETE,
                new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public Page<EventMessageDto> findAll(
            UUID eventId, Pageable pageable, HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url;
        if (requestParam == null) {
            url = requestProperty.getHost() + URL + eventId;
        } else {
            url =
                    requestProperty.getHost()
                            + URL
                            + eventId
                            + requestProperty.getQuestion()
                            + requestParam;
        }
        return restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        new ParameterizedTypeReference<RestResponsePage<EventMessageDto>>() {})
                .getBody();
    }
}
