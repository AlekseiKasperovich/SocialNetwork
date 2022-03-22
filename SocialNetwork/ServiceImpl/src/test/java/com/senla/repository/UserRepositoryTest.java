package com.senla.repository;

import com.senla.model.User;
import static com.senla.prototype.UserPrototype.getUser;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Test of findByEmail method, of class UserRepository.
     */
    @Test
    public void testFindByEmail() {
        User user = getUser();
        roleRepository.save(user.getRole());
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        assertThat(foundUser.get()).isNotNull();
        assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    /**
     * Test of existsByEmail method, of class UserRepository.
     */
    @Test
    public void testExistsByEmail() {
        User user = getUser();
        roleRepository.save(user.getRole());
        userRepository.save(user);
        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }
}
