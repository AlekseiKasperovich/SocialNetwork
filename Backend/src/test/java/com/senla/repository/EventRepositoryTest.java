package com.senla.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.senla.model.Event;
import com.senla.model.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

@DataJpaTest
class EventRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private EventRepository eventRepository;

    @Test
    void findByAuthorId() {
        Optional<User> user = userRepository.findByEmail("user@gmail.com");
        Event event =
                Event.builder().name("Hello").description("Hello world").author(user.get()).build();
        eventRepository.save(event);
        Page<Event> foundEvent = eventRepository.findByAuthorId(user.get().getId(), null);
        assertThat(foundEvent.get().findFirst().get().getName()).isEqualTo("Hello");
    }
}
