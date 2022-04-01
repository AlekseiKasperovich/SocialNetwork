package com.senla.service.custom;

import com.senla.api.dto.сonstants.Roles;
import com.senla.model.Role;
import com.senla.repository.RoleRepository;
import com.senla.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Aliaksei Kaspiarovich
 */
public class RoleServiceTest {

    private RoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleServiceImpl(roleRepository);
    }

    /**
     * Test of findByName method, of class RoleService.
     */
    @Test
    public void testFindByName() {
        Role role = new Role(Roles.ROLE_USER);
        when(roleRepository.findByName(any())).thenReturn(role);
        Role foundRole = roleService.findByName(role.getName());
        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getName()).isEqualTo(role.getName());
    }

}
