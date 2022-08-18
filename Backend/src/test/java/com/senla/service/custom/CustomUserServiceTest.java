package com.senla.service.custom;

import com.senla.exception.UserAlreadyExistException;
import com.senla.exception.UserNotFoundException;
import com.senla.model.User;
import com.senla.service.CustomUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aliaksei Kaspiarovich
 */
public class CustomUserServiceTest extends AbstractIntegrationTest {

    @Autowired
    private CustomUserService userService;

    @Test
    void findUserById() {
    }

    @Test
    public void givenExistingEmail_whenFindingByEmail_thenReturnUser() {
        String email = "user@gmail.com";

        User foundUser = userService.findUserByEmail(email);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(foundUser.getEmail(), email);
    }

    @Test
    public void givenNonExistingEmail_whenFindingByEmail_thenTrowException() {
        String email = "another@gmail.com";

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUserByEmail(email));
    }

    @Test
    public void givenExistingEmail_whenFindingByEmail_thenTrowException() {
        String email = "user@gmail.com";

        Assertions.assertThrows(UserAlreadyExistException.class, () -> userService.existsByEmail(email));
    }

    @Test
    public void givenNonExistingEmail_whenFindingByEmail_thenNotTrowException() {
        String email = "some_any_other@gmail.com";

        Assertions.assertDoesNotThrow(() -> userService.existsByEmail(email));
    }

    @Test
    void save() {
    }
}
