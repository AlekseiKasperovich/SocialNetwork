package com.senla.repository;

import com.senla.model.User;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Aliaksei Kaspiarovich */
class UserRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Test
    void givenExistingEmail_whenFindingByEmail_thenReturnUser() {
        String email = "user@gmail.com";

        Optional<User> foundUser = userRepository.findByEmail(email);

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(email, foundUser.get().getEmail());
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenReturnEmpty() {
        String email = "some_any_other@gmail.com";

        Optional<User> foundUser = userRepository.findByEmail(email);

        Assertions.assertFalse(foundUser.isPresent());
    }

    @Test
    void givenExistingEmail_whenFindingByEmail_thenReturnTrue() {
        String email = "user@gmail.com";

        boolean result = userRepository.existsByEmail(email);

        Assertions.assertTrue(result);
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenReturnFalse() {
        String email = "some_any_other@gmail.com";

        boolean result = userRepository.existsByEmail(email);

        Assertions.assertFalse(result);
    }
}
