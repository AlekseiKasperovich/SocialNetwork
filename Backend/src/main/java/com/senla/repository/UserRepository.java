package com.senla.repository;

import com.senla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * @param email user email
     * @return user
     */
    Optional<User> findByEmail(String email);

    /**
     * @param email user email
     * @return {@literal true} if an entity with the given email exists,
     * {@literal false} otherwise.
     */
    boolean existsByEmail(String email);

}
