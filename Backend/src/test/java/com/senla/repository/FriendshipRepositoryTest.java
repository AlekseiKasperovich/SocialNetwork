package com.senla.repository;

import com.senla.model.Friendship;
import com.senla.model.User;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class FriendshipRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private FriendshipRepository friendshipRepository;

    @Test
    void givenExistingSenderIdAndReceiverId_whenFindingById_thenReturnFriendship() {
        Optional<User> user1 = userRepository.findByEmail("admin@gmail.com");
        Optional<User> user2 = userRepository.findByEmail("user@gmail.com");
        Friendship friendship =
                Friendship.builder()
                        .sender(user1.get())
                        .receiver(user2.get())
                        .accepted(Boolean.TRUE)
                        .build();
        friendshipRepository.save(friendship);

        Optional<Friendship> foundFriendship =
                friendshipRepository.findFriendship(user1.get().getId(), user2.get().getId());

        Assertions.assertTrue(foundFriendship.isPresent());
    }

    @Test
    void givenNonExistingSenderIdAndReceiverId_whenFindingById_thenReturnEmpty() {
        Optional<Friendship> foundFriendship =
                friendshipRepository.findFriendship(UUID.randomUUID(), UUID.randomUUID());

        Assertions.assertFalse(foundFriendship.isPresent());
    }

    @Test
    void givenExistingSenderIdAndReceiverId_whenFindingById_thenReturnFriendshipRequest() {
        Optional<User> user1 = userRepository.findByEmail("admin@gmail.com");
        Optional<User> user2 = userRepository.findByEmail("user@gmail.com");
        Friendship friendship =
                Friendship.builder()
                        .sender(user1.get())
                        .receiver(user2.get())
                        .accepted(Boolean.FALSE)
                        .build();
        friendshipRepository.save(friendship);

        Optional<Friendship> foundFriendshipRequest =
                friendshipRepository.findFriendshipRequest(
                        user1.get().getId(), user2.get().getId());

        Assertions.assertTrue(foundFriendshipRequest.isPresent());
    }

    @Test
    void givenNonExistingUsersId_whenFindingById_thenReturnEmpty() {
        Optional<Friendship> foundFriendshipRequest =
                friendshipRepository.findFriendshipRequest(UUID.randomUUID(), UUID.randomUUID());

        Assertions.assertFalse(foundFriendshipRequest.isPresent());
    }

    @Test
    void givenExistingUserId_whenFindingById_thenReturnMyFriends() {
        Optional<User> user1 = userRepository.findByEmail("admin@gmail.com");
        Optional<User> user2 = userRepository.findByEmail("user@gmail.com");
        Friendship friendship =
                Friendship.builder()
                        .sender(user1.get())
                        .receiver(user2.get())
                        .accepted(Boolean.TRUE)
                        .build();
        friendshipRepository.save(friendship);

        Page<Friendship> myFriends = friendshipRepository.findMyFriends(user2.get().getId(), null);

        Assertions.assertTrue(myFriends.get().findFirst().isPresent());
    }

    @Test
    void givenNonExistingUserId_whenFindingById_thenReturnEmpty() {
        Page<Friendship> myFriends = friendshipRepository.findMyFriends(UUID.randomUUID(), null);

        Assertions.assertFalse(myFriends.get().findFirst().isPresent());
    }

    @Test
    void givenExistingUserId_whenFindingById_thenReturnFriendshipRequests() {
        Optional<User> user1 = userRepository.findByEmail("admin@gmail.com");
        Optional<User> user2 = userRepository.findByEmail("user@gmail.com");
        Friendship friendship =
                Friendship.builder()
                        .sender(user1.get())
                        .receiver(user2.get())
                        .accepted(Boolean.FALSE)
                        .build();
        friendshipRepository.save(friendship);

        Page<Friendship> myFriendshipRequests =
                friendshipRepository.getMyFriendshipRequests(user2.get().getId(), null);

        Assertions.assertTrue(myFriendshipRequests.get().findFirst().isPresent());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenReturnEmpty() {
        Page<Friendship> myFriendshipRequests =
                friendshipRepository.getMyFriendshipRequests(UUID.randomUUID(), null);

        Assertions.assertFalse(myFriendshipRequests.get().findFirst().isPresent());
    }
}
