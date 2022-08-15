package com.senla.client.impl;

import com.senla.client.EventRestClient;
import com.senla.client.HttpHeaderBuilder;
import com.senla.dto.event.CreateEventDto;
import com.senla.dto.event.EventDto;
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
public class EventRestClientImpl implements EventRestClient {

    private static final String URL = "/api/events/";
    private static final String USERS = "/users/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;
    private final RequestProperty requestProperty;

    @Override
    public EventDto getEventById(UUID eventId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId,
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        EventDto.class)
                .getBody();
    }

    @Override
    public EventDto createEvent(CreateEventDto createEventDto) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL,
                        HttpMethod.POST,
                        new HttpEntity<>(createEventDto, httpHeaderBuilder.build()),
                        EventDto.class)
                .getBody();
    }

    @Override
    public EventDto updateEvent(UUID eventId, CreateEventDto createEventDto) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId,
                        HttpMethod.PUT,
                        new HttpEntity<>(createEventDto, httpHeaderBuilder.build()),
                        EventDto.class)
                .getBody();
    }

    @Override
    public void deleteEvent(UUID eventId) {
        restTemplate.exchange(
                requestProperty.getHost() + URL + eventId,
                HttpMethod.DELETE,
                new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public EventDto addUser(UUID eventId, UUID userId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId + USERS + userId,
                        HttpMethod.PUT,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        EventDto.class)
                .getBody();
    }

    @Override
    public EventDto deleteUser(UUID eventId, UUID userId) {
        return restTemplate
                .exchange(
                        requestProperty.getHost() + URL + eventId + USERS + userId,
                        HttpMethod.DELETE,
                        new HttpEntity<>(httpHeaderBuilder.build()),
                        EventDto.class)
                .getBody();
    }

    @Override
    public Page<EventDto> findMyEvents(Pageable pageable, HttpServletRequest request) {
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
                        new ParameterizedTypeReference<RestResponsePage<EventDto>>() {})
                .getBody();
    }
}
