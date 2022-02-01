package com.senla.impl.service;

import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import com.senla.api.exception.MessageNotFoundException;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.api.service.MessageService;
import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.Message;
import com.senla.impl.model.User;
import com.senla.impl.repository.MessageRepository;
import com.senla.impl.service.custom.CustomUserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final CustomUserService userService;
    private final MessageRepository messageRepository;
    private final Mapper mapper;

    /**
     *
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
     *
     * @param messageId message ID
     * @return message
     */
    @Override
    public MessageDto getMessageById(Long messageId) {
        Message message = findMessageById(messageId);
        User user = userService.getCurrentUser();
        if (message.getSender().getId().equals(user.getId())
                || message.getReceiver().getId().equals(user.getId())) {
            return mapper.map(message, MessageDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     *
     * @param receiverId receiver ID
     * @param createMessageDto message body
     * @return message
     */
    @Override
    public MessageDto createMessage(Long receiverId, CreateMessageDto createMessageDto) {
        User user = userService.getCurrentUser();
        Message message = Message.builder()
                .posted(LocalDateTime.now())
                .sender(user)
                .receiver(userService.findUserById(receiverId))
                .body(createMessageDto.getBody())
                .isPrivate(Boolean.TRUE)
                .build();
        if (user.getId().equals(receiverId)) {
            message.setIsPrivate(Boolean.FALSE);
        }
        return mapper.map(messageRepository.save(message), MessageDto.class);
    }

    /**
     *
     * @param messageId message ID
     * @param createMessageDto message body
     * @return updated message
     */
    @Override
    public MessageDto updateMessage(Long messageId,
            CreateMessageDto createMessageDto) {
        Message message = findMessageById(messageId);
        User user = userService.getCurrentUser();
        if (message.getSender().getId().equals(user.getId())) {
            message.setBody(createMessageDto.getBody());
            return mapper.map(messageRepository.save(message), MessageDto.class);
        }
        throw new MyAccessDeniedException("Access is denied");
    }

    /**
     *
     * @param messageId message ID
     */
    @Override
    public void deleteMessage(Long messageId) {
        Message message = findMessageById(messageId);
        User user = userService.getCurrentUser();
        if (message.getSender().getId().equals(user.getId())) {
            messageRepository.deleteById(messageId);
        } else {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

    /**
     *
     * @param receiverId receiver ID
     * @param pageable pagination information
     * @return messages
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MessageDto> findAll(Long receiverId, Pageable pageable) {
        User user = userService.getCurrentUser();
        Page<Message> messagePage = messageRepository.findMessages(user.getId(), receiverId, pageable);
        return messagePage.map(message -> mapper.map(message, MessageDto.class));
    }

}
