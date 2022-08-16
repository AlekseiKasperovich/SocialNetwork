package com.senla.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.senla.model.Message;
import com.senla.model.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class MessageRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private MessageRepository messageRepository;

    @Test
    void findMessages() {
        Optional<User> user1 = userRepository.findByEmail("admin@gmail.com");
        Optional<User> user2 = userRepository.findByEmail("user@gmail.com");
        Message message =
                Message.builder()
                        .body("Hello")
                        .posted(LocalDateTime.now())
                        .sender(user1.get())
                        .receiver(user2.get())
                        .isPrivate(Boolean.TRUE)
                        .build();
        messageRepository.save(message);
        Page<Message> foundMessage =
                messageRepository.findMessages(user1.get().getId(), user2.get().getId(), null);
        assertThat(foundMessage.get().findFirst().get().getBody()).isEqualTo("Hello");
    }
}
