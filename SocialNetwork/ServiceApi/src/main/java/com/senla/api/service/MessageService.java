package com.senla.api.service;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface MessageService {

    MessageDto getMessageById(Long messageId);

    MessageDto createMessage(Long receiverId, CreateMessageDto createMessageDto);

    MessageDto updateMessage(Long messageId, CreateMessageDto createMessageDto);

    void deleteMessage(Long messageId);

    Page<MessageDto> findAll(Long receiverId, Pageable pageable);

}
