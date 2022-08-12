package com.senla.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.senla.dto.constants.Roles;
import com.senla.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/** @author Aliaksei Kaspiarovich */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired private RoleRepository roleRepository;

    /** Test of findByName method, of class RoleRepository. */
    @Test
    public void testFindByName() {
        Role role = new Role(Roles.ROLE_USER);
        roleRepository.save(role);
        Role foundRole = roleRepository.findByName(role.getName());
        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getName()).isEqualTo(role.getName());
    }
}
