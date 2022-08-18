package com.senla.service.custom;

import com.senla.dto.constants.Roles;
import com.senla.model.Role;
import com.senla.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @author Aliaksei Kaspiarovich */
public class RoleServiceTest extends AbstractIntegrationTest {

    @Autowired private RoleService roleService;

    @Test
    public void givenExistingRoleName_whenFindingByName_thenReturnRole() {
        Role foundRole = roleService.findByName(Roles.ROLE_USER);
        Assertions.assertNotNull(foundRole);
        Assertions.assertEquals(foundRole.getName(), Roles.ROLE_USER);
    }
}
