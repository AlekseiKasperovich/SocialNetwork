package com.senla.client;

import com.senla.dto.message.CreateMessageDto;
import com.senla.dto.message.MessageDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface MessageRestClient {

    MessageDto getMessageById(UUID messageId);

    MessageDto createMessage(UUID receiverId, CreateMessageDto createMessageDto);

    MessageDto updateMessage(UUID messageId, CreateMessageDto createMessageDto);

    void deleteMessage(UUID messageId);

    Page<MessageDto> findAll(UUID receiverId, Pageable pageable, HttpServletRequest request);
}
