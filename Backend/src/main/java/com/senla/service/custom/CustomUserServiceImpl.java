package com.senla.service.custom;

import com.senla.exception.UserAlreadyExistException;
import com.senla.exception.UserNotFoundException;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.CustomUserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserServiceImpl implements CustomUserService {

    private final UserRepository userRepository;

    /**
     * @param id user ID
     * @return user
     */
    @Override
    public User findUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new UserNotFoundException(
                                        String.format("User with id = %s is not found", id)));
    }

    /**
     * @param email user email
     * @return user
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () ->
                                new UserNotFoundException(
                                        String.format("User with email = %s is not found", email)));
    }

    /** @param email user email */
    @Override
    public void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistException(
                    String.format("There is an account with that email address: = %s", email));
        }
    }

    /**
     * @param user user
     * @return user
     */
    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findBannedUsers(Pageable pageable) {
        return userRepository.findBannedUsers(pageable);
    }
}
