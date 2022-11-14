package com.senla.service.impl;

import com.senla.dto.message.CreateMessageDto;
import com.senla.dto.message.MessageDto;
import com.senla.exception.MessageNotFoundException;
import com.senla.exception.MyAccessDeniedException;
import com.senla.mapper.Mapper;
import com.senla.model.Message;
import com.senla.repository.MessageRepository;
import com.senla.service.CustomUserService;
import com.senla.service.MessageService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final CustomUserService userService;
    private final MessageRepository messageRepository;
    private final Mapper mapper;

    private static final String EXCEPTION_MESSAGE = "Access is denied";

    /**
     * @param id message ID
     * @return message
     */
    @Transactional(readOnly = true)
    public Message findMessageById(UUID id) {
        return messageRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new MessageNotFoundException(
                                        String.format("Message with id = %s is not found", id)));
    }

    /**
     * @param messageId message ID
     * @param id id
     * @return message
     */
    @Override
    public MessageDto getMessageById(UUID messageId, UUID id) {
        Message message = findMessageById(messageId);
        if (message.getSender().getId().equals(id) || message.getReceiver().getId().equals(id)) {
            return mapper.map(message, MessageDto.class);
        }
        throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
    }

    /**
     * @param receiverId receiver ID
     * @param createMessageDto message body
     * @param id id
     * @return message
     */
    @Override
    public MessageDto createMessage(UUID receiverId, CreateMessageDto createMessageDto, UUID id) {
        Message message =
                Message.builder()
                        .posted(LocalDateTime.now())
                        .sender(userService.findUserById(id))
                        .receiver(userService.findUserById(receiverId))
                        .body(createMessageDto.getBody())
                        .isPrivate(Boolean.TRUE)
                        .build();
        if (id.equals(receiverId)) {
            message.setIsPrivate(Boolean.FALSE);
        }
        return mapper.map(messageRepository.save(message), MessageDto.class);
    }

    /**
     * @param messageId message ID
     * @param createMessageDto message body
     * @param id id
     * @return updated message
     */
    @Override
    public MessageDto updateMessage(UUID messageId, CreateMessageDto createMessageDto, UUID id) {
        Message message = findMessageById(messageId);
        if (message.getSender().getId().equals(id)) {
            message.setBody(createMessageDto.getBody());
            return mapper.map(messageRepository.save(message), MessageDto.class);
        }
        throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
    }

    /**
     * @param messageId message ID
     * @param id id
     */
    @Override
    public void deleteMessage(UUID messageId, UUID id) {
        Message message = findMessageById(messageId);
        if (message.getSender().getId().equals(id)) {
            messageRepository.deleteById(messageId);
        } else {
            throw new MyAccessDeniedException(EXCEPTION_MESSAGE);
        }
    }

    /**
     * @param receiverId receiver ID
     * @param id id
     * @param pageable pagination information
     * @return messages
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MessageDto> findMyMessages(UUID receiverId, UUID id, Pageable pageable) {
        Page<Message> messagePage = messageRepository.findMessages(id, receiverId, pageable);
        return messagePage.map(message -> mapper.map(message, MessageDto.class));
    }

    @Override
    public void send(String messageText) {
        log.info("Message sent from MessageService");
    }
}
