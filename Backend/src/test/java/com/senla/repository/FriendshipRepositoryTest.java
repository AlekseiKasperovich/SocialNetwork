package com.senla.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.senla.model.Friendship;
import com.senla.model.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

@DataJpaTest
class FriendshipRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private FriendshipRepository friendshipRepository;

    @Test
    void findFriendship() {
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
        assertThat(foundFriendship.get()).isNotNull();
    }

    @Test
    void findFriendshipRequest() {
        Optional<User> user1 = userRepository.findByEmail("admin@gmail.com");
        Optional<User> user2 = userRepository.findByEmail("user@gmail.com");
        Friendship friendship =
                Friendship.builder()
                        .sender(user1.get())
                        .receiver(user2.get())
                        .accepted(Boolean.FALSE)
                        .build();
        friendshipRepository.save(friendship);
        Optional<Friendship> foundFriendship =
                friendshipRepository.findFriendshipRequest(
                        user1.get().getId(), user2.get().getId());
        assertThat(foundFriendship.get()).isNotNull();
    }

    @Test
    void findMyFriends() {
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
        assertThat(myFriends.get().findFirst().get()).isNotNull();
    }

    @Test
    void getMyFriendshipRequests() {
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
        assertThat(myFriendshipRequests.get().findFirst().get()).isNotNull();
    }
}
