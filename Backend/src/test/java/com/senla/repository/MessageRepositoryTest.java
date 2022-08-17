package com.senla.repository;

import com.senla.model.Message;
import com.senla.model.User;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class MessageRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private MessageRepository messageRepository;

    @Test
    void givenExistingSenderIdAndReceiverId_whenFindingById_thenReturnMessages() {
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

        Assertions.assertEquals(foundMessage.get().findFirst().get().getBody(), "Hello");
    }

    @Test
    void givenNonExistingSenderIdAndReceiverId_whenFindingById_thenReturnEmpty() {
        Page<Message> foundMessage =
                messageRepository.findMessages(UUID.randomUUID(), UUID.randomUUID(), null);

        Assertions.assertFalse(foundMessage.get().findFirst().isPresent());
    }
}
