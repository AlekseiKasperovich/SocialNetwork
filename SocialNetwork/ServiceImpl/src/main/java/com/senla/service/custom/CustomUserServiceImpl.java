package com.senla.service.custom;

import com.senla.api.exception.UserAlreadyExistException;
import com.senla.api.exception.UserNotFoundException;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserServiceImpl implements CustomUserService {

    private final UserRepository userRepository;

    /**
     *
     * @param id user ID
     * @return user
     */
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()
                        -> new UserNotFoundException(
                        String.format("User with id = %s is not found", id)));
    }

    /**
     *
     * @param email user email
     * @return user
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()
                        -> new UserNotFoundException(
                        String.format("User with email = %s is not found", email)));
    }

    /**
     *
     * @param email user email
     */
    public void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistException(
                    String.format("There is an account with that email address: = %s",
                            email));
        }
    }

    /**
     *
     * @param user user
     * @return user
     */
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

}
