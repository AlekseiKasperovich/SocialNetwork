package com.senla.service;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface MessageService {

    MessageDto getMessageById(Long messageId, Long id);

    MessageDto createMessage(Long receiverId, CreateMessageDto createMessageDto, Long id);

    MessageDto updateMessage(Long messageId, CreateMessageDto createMessageDto, Long id);

    void deleteMessage(Long messageId, Long id);

    Page<MessageDto> findAll(Long receiverId, Long id, Pageable pageable);

    void send(String messageText);
}
