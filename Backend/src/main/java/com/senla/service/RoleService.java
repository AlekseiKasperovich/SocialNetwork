package com.senla.service;

import com.senla.dto.constants.Roles;
import com.senla.model.Role;

/** @author Aliaksei Kaspiarovich */
public interface RoleService {

    Role findByName(Roles name);
}
