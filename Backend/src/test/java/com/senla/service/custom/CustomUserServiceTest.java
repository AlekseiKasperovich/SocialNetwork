package com.senla.service.custom;

import com.senla.dto.constants.Roles;
import com.senla.dto.constants.Status;
import com.senla.exception.UserAlreadyExistException;
import com.senla.exception.UserNotFoundException;
import com.senla.model.User;
import com.senla.service.CustomUserService;
import com.senla.service.RoleService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

/** @author Aliaksei Kaspiarovich */
class CustomUserServiceTest extends AbstractIntegrationTest {

    @Autowired private CustomUserService userService;

    @Autowired private RoleService roleService;

    @Test
    void givenExistingId_whenFindingById_thenReturnUser() {
        String email = "user@gmail.com";
        User foundUser = userService.findUserByEmail(email);

        User user = userService.findUserById(foundUser.getId());

        Assertions.assertNotNull(user);
        Assertions.assertEquals(foundUser.getId(), user.getId());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() {
        Assertions.assertThrows(
                UserNotFoundException.class, () -> userService.findUserById(UUID.randomUUID()));
    }

    @Test
    void givenExistingEmail_whenFindingByEmail_thenReturnUser() {
        String email = "user@gmail.com";

        User foundUser = userService.findUserByEmail(email);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(email, foundUser.getEmail());
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenTrowException() {
        String email = "another@gmail.com";

        Assertions.assertThrows(
                UserNotFoundException.class, () -> userService.findUserByEmail(email));
    }

    @Test
    void givenExistingEmail_whenFindingByEmail_thenTrowException() {
        String email = "user@gmail.com";

        Assertions.assertThrows(
                UserAlreadyExistException.class, () -> userService.existsByEmail(email));
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenNotTrowException() {
        String email = "some_any_other@gmail.com";

        Assertions.assertDoesNotThrow(() -> userService.existsByEmail(email));
    }

    @Test
    void givenUser_whenSave_thenReturnUser() {
        User user =
                User.builder()
                        .email("test@gmail.com")
                        .password("password")
                        .status(Status.ACTIVE)
                        .role(roleService.findByName(Roles.ROLE_USER))
                        .build();

        User saveUser = userService.save(user);

        Assertions.assertNotNull(saveUser);
        Assertions.assertEquals(user.getEmail(), saveUser.getEmail());
    }

    @Test
    void givenUserWithoutRole_whenSave_thenTrowException() {
        User user =
                User.builder()
                        .email("test@gmail.com")
                        .password("password")
                        .status(Status.ACTIVE)
                        .build();

        Assertions.assertThrows(
                DataIntegrityViolationException.class, () -> userService.save(user));
    }
}
