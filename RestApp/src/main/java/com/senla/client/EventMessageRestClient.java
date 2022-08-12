package com.senla.client;

import com.senla.dto.event.EventMessageDto;
import com.senla.dto.message.CreateMessageDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface EventMessageRestClient {

    EventMessageDto createEventMessage(Long eventId, CreateMessageDto createMessageDto);

    EventMessageDto getEventMessageById(Long eventId, Long messageId);

    EventMessageDto updateEventMessage(
            Long eventId, Long messageId, CreateMessageDto createMessageDto);

    void deleteEventMessage(Long eventId, Long messageId);

    Page<EventMessageDto> findAll(Long eventId, Pageable pageable, HttpServletRequest request);
}
