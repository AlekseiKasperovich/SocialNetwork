package com.senla.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.senla.dto.constants.Roles;
import com.senla.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Aliaksei Kaspiarovich */
class RoleRepositoryTest extends DatabaseTest {

    @Autowired private RoleRepository roleRepository;

    /** Test of findByName method, of class RoleRepository. */
    @Test
    public void testFindByName() {
        Role foundRole = roleRepository.findByName(Roles.ROLE_USER);
        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getName()).isEqualTo(Roles.ROLE_USER);
    }
}
