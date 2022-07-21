package com.senla.service;

import com.senla.api.dto.сonstants.Roles;
import com.senla.model.Role;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface RoleService {

    Role findByName(Roles name);
}
