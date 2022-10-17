package com.senla.service;

import com.senla.model.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface CustomUserService {

    User findUserById(UUID id);

    User findUserByEmail(String email);

    void existsByEmail(String email);

    User save(User user);

    Page<User> findBannedUsers(Pageable pageable);
}
