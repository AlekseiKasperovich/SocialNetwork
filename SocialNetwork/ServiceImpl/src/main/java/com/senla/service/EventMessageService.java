package com.senla.service;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface EventMessageService {

    EventMessageDto createEventMessage(Long eventId, CreateMessageDto createMessageDto, String email);

    EventMessageDto getEventMessageById(Long eventId, Long messageId, String email);

    EventMessageDto updateEventMessage(Long eventId, Long messageId,
                                       CreateMessageDto createMessageDto, String email);

    void deleteEventMessage(Long eventId, Long messageId, String email);

    Page<EventMessageDto> findAll(Long eventId, String email, Pageable pageable);

}
