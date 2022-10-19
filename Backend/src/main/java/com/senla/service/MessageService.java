package com.senla.service;

import com.senla.dto.message.CreateMessageDto;
import com.senla.dto.message.MessageDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface MessageService {

    MessageDto getMessageById(UUID messageId, UUID id);

    MessageDto createMessage(UUID receiverId, CreateMessageDto createMessageDto, UUID id);

    MessageDto updateMessage(UUID messageId, CreateMessageDto createMessageDto, UUID id);

    void deleteMessage(UUID messageId, UUID id);

    Page<MessageDto> findMyMessages(UUID receiverId, UUID id, Pageable pageable);

    void send(String messageText);
}
