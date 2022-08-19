package com.senla.repository;

import com.senla.model.Event;
import com.senla.model.User;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class EventRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private EventRepository eventRepository;

    @Test
    void givenExistingAuthorId_whenFindingById_thenReturnAuthor() {
        Optional<User> user = userRepository.findByEmail("user@gmail.com");
        Event event =
                Event.builder().name("Hello").description("Hello world").author(user.get()).build();
        eventRepository.save(event);

        Page<Event> foundEvent = eventRepository.findByAuthorId(user.get().getId(), null);

        Assertions.assertEquals("Hello", foundEvent.get().findFirst().get().getName());
    }

    @Test
    void givenNonExistingAuthorId_whenFindingById_thenReturnEmpty() {
        Page<Event> foundEvent = eventRepository.findByAuthorId(UUID.randomUUID(), null);

        Assertions.assertFalse(foundEvent.get().findFirst().isPresent());
    }
}
