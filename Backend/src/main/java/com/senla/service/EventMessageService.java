package com.senla.service;

import com.senla.dto.event.EventMessageDto;
import com.senla.dto.message.CreateMessageDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface EventMessageService {

    EventMessageDto createEventMessage(UUID eventId, CreateMessageDto createMessageDto, UUID id);

    EventMessageDto getEventMessageById(UUID eventId, UUID messageId, UUID id);

    EventMessageDto updateEventMessage(
            UUID eventId, UUID messageId, CreateMessageDto createMessageDto, UUID id);

    void deleteEventMessage(UUID eventId, UUID messageId, UUID id);

    Page<EventMessageDto> findAll(UUID eventId, UUID id, Pageable pageable);
}
