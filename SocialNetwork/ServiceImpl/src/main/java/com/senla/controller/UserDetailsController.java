package com.senla.controller;

import com.senla.api.dto.user.UserDetailsDto;
import com.senla.service.IUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/users/details",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserDetailsController {

    private final IUserDetailService userDetailService;

    /**
     * @param email
     * @return
     */
    @GetMapping
    public UserDetailsDto getUserByEmail(@RequestHeader("${request.email}") String email) {
        return userDetailService.getUserByEmail(email);
    }

}
