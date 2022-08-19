package com.senla.service.custom;

import com.senla.exception.FriendshipNotFoundException;
import com.senla.model.Friendship;
import com.senla.model.User;
import com.senla.repository.FriendshipRepository;
import com.senla.service.CustomFriendshipService;
import com.senla.service.CustomUserService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CustomFriendshipServiceTest extends AbstractIntegrationTest {

    @Autowired private CustomFriendshipService customFriendshipService;

    @Autowired private FriendshipRepository friendshipRepository;

    @Autowired private CustomUserService userService;

    @Test
    void givenExistingId_whenFindingById_thenReturnFriendship() {
        User user1 = userService.findUserByEmail("admin@gmail.com");
        User user2 = userService.findUserByEmail("user@gmail.com");
        Friendship friendship =
                Friendship.builder().sender(user1).receiver(user2).accepted(Boolean.TRUE).build();
        Friendship saveFriendship = friendshipRepository.save(friendship);

        Friendship foundFriendship =
                customFriendshipService.findFriendshipById(saveFriendship.getId());

        Assertions.assertNotNull(foundFriendship);
        Assertions.assertEquals(saveFriendship.getId(), foundFriendship.getId());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() {
        Assertions.assertThrows(
                FriendshipNotFoundException.class,
                () -> customFriendshipService.findFriendshipById(UUID.randomUUID()));
    }
}
