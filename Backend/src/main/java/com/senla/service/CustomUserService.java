package com.senla.service;

import com.senla.model.User;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface CustomUserService {

    User findUserById(UUID id);

    User findUserByEmail(String email);

    void existsByEmail(String email);

    User save(User user);
}
