package com.senla.client.impl;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.client.EventMessageRestClient;
import com.senla.client.HttpHeaderBuilder;
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
public class EventMessageRestClientImpl implements EventMessageRestClient {

    private static final String URL = "/api/events/";
    private static final String MESSAGES = "/messages/";
    private final RestTemplate restTemplate;
    private final HttpHeaderBuilder httpHeaderBuilder;

    @Override
    public EventMessageDto getEventMessageById(Long eventId, Long messageId) {
        return restTemplate.exchange("${request.host}" + URL + eventId + MESSAGES + messageId,
                HttpMethod.GET, new HttpEntity<>(httpHeaderBuilder.build()),
                EventMessageDto.class).getBody();
    }

    @Override
    public EventMessageDto createEventMessage(Long eventId,
                                              CreateMessageDto createMessageDto) {
        return restTemplate.exchange("${request.host}" + URL + eventId,
                HttpMethod.POST,
                new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                EventMessageDto.class).getBody();
    }

    @Override
    public EventMessageDto updateEventMessage(Long eventId, Long messageId,
                                              CreateMessageDto createMessageDto) {
        return restTemplate.exchange("${request.host}" + URL + eventId + MESSAGES + messageId,
                HttpMethod.PUT,
                new HttpEntity<>(createMessageDto, httpHeaderBuilder.build()),
                EventMessageDto.class).getBody();
    }

    @Override
    public void deleteEventMessage(Long eventId, Long messageId) {
        restTemplate.exchange("${request.host}" + URL + eventId + MESSAGES + messageId,
                HttpMethod.DELETE, new HttpEntity<>(httpHeaderBuilder.build()),
                Void.class);
    }

    @Override
    public Page<EventMessageDto> findAll(Long eventId, Pageable pageable,
                                         HttpServletRequest request) {
        String requestParam = request.getQueryString();
        String url = null;
        if (requestParam == null) {
            url = "${request.host}" + URL + eventId;
        } else {
            url = "${request.host}" + URL + eventId + "${request.question}" + requestParam;
        }
        return restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(httpHeaderBuilder.build()),
                new ParameterizedTypeReference<RestResponsePage<EventMessageDto>>() {
                }).getBody();
    }
}
