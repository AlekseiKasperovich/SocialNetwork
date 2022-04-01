package com.senla.service;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface MessageService {

    MessageDto getMessageById(Long messageId, String email);

    MessageDto createMessage(Long receiverId, CreateMessageDto createMessageDto, String email);

    MessageDto updateMessage(Long messageId, CreateMessageDto createMessageDto, String email);

    void deleteMessage(Long messageId, String email);

    Page<MessageDto> findAll(Long receiverId, String email, Pageable pageable);

}
