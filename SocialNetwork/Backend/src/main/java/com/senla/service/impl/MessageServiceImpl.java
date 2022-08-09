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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Aliaksei Kaspiarovich
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final CustomUserService userService;
    private final MessageRepository messageRepository;
    private final Mapper mapper;

    /**
     * @param id message ID
     * @return message
     */
    @Transactional(readOnly = true)
    public Message findMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(
                () -> new MessageNotFoundException(
                        String.format("Message with id = %s is not found", id)));
    }

    /**
     * @param messageId message ID
     * @param id        id
     * @return message
     */
    @Override
    public MessageDto getMessageById(Long messageId, Long id) {
        Message message = findMessageById(messageId);
        if (message.getSender().getId().equals(id) || message.getReceiver().getId().equals(id)) {
            return mapper.map(message, MessageDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     * @param receiverId       receiver ID
     * @param createMessageDto message body
     * @param id               id
     * @return message
     */
    @Override
    public MessageDto createMessage(Long receiverId, CreateMessageDto createMessageDto, Long id) {
        Message message = Message.builder()
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
     * @param messageId        message ID
     * @param createMessageDto message body
     * @param id               id
     * @return updated message
     */
    @Override
    public MessageDto updateMessage(Long messageId, CreateMessageDto createMessageDto, Long id) {
        Message message = findMessageById(messageId);
        if (message.getSender().getId().equals(id)) {
            message.setBody(createMessageDto.getBody());
            return mapper.map(messageRepository.save(message), MessageDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     * @param messageId message ID
     * @param id        id
     */
    @Override
    public void deleteMessage(Long messageId, Long id) {
        Message message = findMessageById(messageId);
        if (message.getSender().getId().equals(id)) {
            messageRepository.deleteById(messageId);
        } else {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

    /**
     * @param receiverId receiver ID
     * @param id      id
     * @param pageable   pagination information
     * @return messages
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MessageDto> findAll(Long receiverId, Long id, Pageable pageable) {
        Page<Message> messagePage = messageRepository.findMessages(id, receiverId, pageable);
        return messagePage.map(message -> mapper.map(message, MessageDto.class));
    }

    @Override
    public void send(String messageText) {
        log.info("Message sent from MessageService");
    }

}
