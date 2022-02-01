package com.senla.rest.controller;

import com.senla.api.dto.user.DtoUser;
import com.senla.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     *
     * @param id user ID
     * @return user
     */
    @GetMapping("{id}")
    public DtoUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     *
     * @param firstName First Name
     * @param lastName Last Name
     * @param pageable pagination information
     * @return users
     */
    @GetMapping
    public Page<DtoUser> searchUsers(@RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName, Pageable pageable) {
        return userService.searchUsers(firstName, lastName, pageable);
    }

}
