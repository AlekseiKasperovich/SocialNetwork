package com.senla.service.custom;

import com.senla.model.Role;
import com.senla.api.dto.сonstants.Roles;
import com.senla.repository.RoleRepository;
import com.senla.service.RoleService;
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
public class RoleServiceImpl implements RoleService{

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
