package com.senla.impl.repository;

import com.senla.impl.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     *
     * @param email user email
     * @return user
     */
    Optional<User> findByEmail(String email);

    /**
     *
     * @param email user email
     * @return {@literal true} if an entity with the given email exists,
     * {@literal false} otherwise.
     */
    Boolean existsByEmail(String email);

}
