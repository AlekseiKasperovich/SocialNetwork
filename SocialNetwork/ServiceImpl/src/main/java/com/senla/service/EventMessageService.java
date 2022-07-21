package com.senla.service;

import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.message.CreateMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface EventMessageService {

    EventMessageDto createEventMessage(Long eventId, CreateMessageDto createMessageDto, Long id);

    EventMessageDto getEventMessageById(Long eventId, Long messageId, Long id);

    EventMessageDto updateEventMessage(Long eventId, Long messageId,
                                       CreateMessageDto createMessageDto, Long id);

    void deleteEventMessage(Long eventId, Long messageId, Long id);

    Page<EventMessageDto> findAll(Long eventId, Long id, Pageable pageable);

}
