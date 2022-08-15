package com.senla.repository;

import com.senla.dto.constants.Roles;
import com.senla.model.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** @author Aliaksei Kaspiarovich */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * @param name role name
     * @return role
     */
    Role findByName(Roles name);
}
