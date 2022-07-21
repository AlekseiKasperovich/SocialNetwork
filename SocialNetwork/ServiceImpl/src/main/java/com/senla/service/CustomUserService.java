package com.senla.service;

import com.senla.model.User;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface CustomUserService {

    User findUserById(Long id);

    User findUserByEmail(String email);

    void existsByEmail(String email);

    User save(User user);
}
