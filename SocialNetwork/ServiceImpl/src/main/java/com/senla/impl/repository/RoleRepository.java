package com.senla.impl.repository;

import com.senla.impl.model.Role;
import com.senla.impl.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     *
     * @param name role name
     * @return role
     */
    Role findByName(Roles name);
}
