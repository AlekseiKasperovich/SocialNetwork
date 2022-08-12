package com.senla.client;

import com.senla.dto.message.CreateMessageDto;
import com.senla.dto.message.MessageDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface MessageRestClient {

    MessageDto getMessageById(Long messageId);

    MessageDto createMessage(Long receiverId, CreateMessageDto createMessageDto);

    MessageDto updateMessage(Long messageId, CreateMessageDto createMessageDto);

    void deleteMessage(Long messageId);

    Page<MessageDto> findAll(Long receiverId, Pageable pageable, HttpServletRequest request);
}
