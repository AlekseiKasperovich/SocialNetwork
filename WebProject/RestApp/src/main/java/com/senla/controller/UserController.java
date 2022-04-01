package com.senla.controller;

import com.senla.api.dto.user.DtoUser;
import com.senla.client.UserRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserRestClient userRestClient;

    /**
     * @param id user ID
     * @return user
     */
    @GetMapping("{id}")
    public DtoUser getUserById(@PathVariable Long id) {
        return userRestClient.getUserById(id);
    }

    /**
     * @param firstName First Name
     * @param lastName  Last Name
     * @param pageable  pagination information
     * @param request
     * @return users
     */
    @GetMapping
    public Page<DtoUser> searchUsers(@RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName, Pageable pageable,
                                     HttpServletRequest request) {
        return userRestClient.searchUsers(pageable, request);
    }

}
