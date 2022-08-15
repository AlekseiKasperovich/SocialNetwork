package com.senla.client;

import com.senla.dto.event.EventMessageDto;
import com.senla.dto.message.CreateMessageDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface EventMessageRestClient {

    EventMessageDto createEventMessage(UUID eventId, CreateMessageDto createMessageDto);

    EventMessageDto getEventMessageById(UUID eventId, UUID messageId);

    EventMessageDto updateEventMessage(
            UUID eventId, UUID messageId, CreateMessageDto createMessageDto);

    void deleteEventMessage(UUID eventId, UUID messageId);

    Page<EventMessageDto> findAll(UUID eventId, Pageable pageable, HttpServletRequest request);
}
