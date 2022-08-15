package com.senla.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.senla.dto.constants.Roles;
import com.senla.model.User;
import com.senla.prototype.UserPrototype;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/** @author Aliaksei Kaspiarovich */
@DataJpaTest
public class UserRepositoryTest extends DatabaseTest {

    @Autowired private UserRepository userRepository;

    @Autowired private RoleRepository roleRepository;

    /** Test of findByEmail method, of class UserRepository. */
    @Test
    public void testFindByEmail() {
        User user = UserPrototype.getUser();
        user.setRole(roleRepository.findByName(Roles.ROLE_USER));
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        assertThat(foundUser.get()).isNotNull();
        assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    /** Test of existsByEmail method, of class UserRepository. */
    @Test
    public void testExistsByEmail() {
        User user = UserPrototype.getUser();
        user.setRole(roleRepository.findByName(Roles.ROLE_USER));
        userRepository.save(user);
        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }
}
