package com.senla.service.impl;

import static org.springframework.data.jpa.domain.Specification.where;

import com.senla.dto.user.DtoUser;
import com.senla.mapper.Mapper;
import com.senla.model.User;
import com.senla.model.User_;
import com.senla.repository.UserRepository;
import com.senla.service.CustomUserService;
import com.senla.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final CustomUserService userService;
    private final UserRepository userRepository;
    private final Mapper mapper;

    /**
     * @param id user ID
     * @return user
     */
    @Override
    public DtoUser getUserById(UUID id) {
        return mapper.map(userService.findUserById(id), DtoUser.class);
    }

    /**
     * @param firstName First Name
     * @param lastName Last Name
     * @param pageable pagination information
     * @return users
     */
    @Override
    public Page<DtoUser> searchUsers(String firstName, String lastName, Pageable pageable) {
        if (firstName == null && lastName == null) {
            Page<User> userPage = userRepository.findAll(pageable);
            return userPage.map(user -> mapper.map(user, DtoUser.class));
        }
        Specification<User> specification = null;
        if (firstName != null) {
            Specification<User> specification1 =
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(User_.firstName)),
                                    "%" + firstName.toLowerCase() + "%");
            specification = specification1;
        }
        if (lastName != null) {
            Specification<User> specification2 =
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(User_.lastName)),
                                    "%" + lastName.toLowerCase() + "%");
            if (specification != null) {
                specification = specification.or(specification2);
            } else {
                specification = specification2;
            }
        }
        Page<User> userPage = userRepository.findAll(where(specification), pageable);
        return userPage.map(user -> mapper.map(user, DtoUser.class));
    }
}
