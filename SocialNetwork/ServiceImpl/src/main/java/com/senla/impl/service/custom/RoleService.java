package com.senla.impl.service.custom;

import com.senla.impl.model.Role;
import com.senla.impl.model.enums.Roles;
import com.senla.impl.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     *
     * @param name role name
     * @return role
     */
    public Role findByName(Roles name) {
        return roleRepository.findByName(name);
    }
}
