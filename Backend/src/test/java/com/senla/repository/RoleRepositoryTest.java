package com.senla.repository;

import com.senla.dto.constants.Roles;
import com.senla.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Aliaksei Kaspiarovich */
class RoleRepositoryTest extends DatabaseTest {

    @Autowired private RoleRepository roleRepository;

    @Test
    void givenExistingRoleName_whenFindingByName_thenReturnRole() {
        Role foundRole = roleRepository.findByName(Roles.ROLE_USER);
        Assertions.assertNotNull(foundRole);
        Assertions.assertEquals(Roles.ROLE_USER, foundRole.getName());
    }
}
