package com.senla.repository;

import com.senla.model.Role;
import com.senla.api.dto.сonstants.Roles;
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
