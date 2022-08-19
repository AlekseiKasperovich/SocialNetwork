package com.senla.service.custom;

import com.senla.exception.EventNotFoundException;
import com.senla.model.Event;
import com.senla.model.User;
import com.senla.repository.EventRepository;
import com.senla.service.CustomEventService;
import com.senla.service.CustomUserService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CustomEventServiceTest extends AbstractIntegrationTest {

    @Autowired private CustomEventService customEventService;

    @Autowired private EventRepository eventRepository;

    @Autowired private CustomUserService userService;

    @Test
    void givenExistingId_whenFindingById_thenReturnEvent() {
        User user = userService.findUserByEmail("user@gmail.com");
        Event event = Event.builder().name("Hello").description("Hello world").author(user).build();
        Event saveEvent = eventRepository.save(event);

        Event foundEvent = customEventService.findEventById(saveEvent.getId());

        Assertions.assertNotNull(foundEvent);
        Assertions.assertEquals(saveEvent.getId(), foundEvent.getId());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() {
        Assertions.assertThrows(
                EventNotFoundException.class,
                () -> customEventService.findEventById(UUID.randomUUID()));
    }
}
