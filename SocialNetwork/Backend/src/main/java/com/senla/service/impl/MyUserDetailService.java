package com.senla.service.impl;

import com.senla.dto.user.UserDetailsDto;
import com.senla.mapper.Mapper;
import com.senla.service.CustomUserService;
import com.senla.service.IUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailService implements IUserDetailService {

    private final CustomUserService userService;
    private final Mapper mapper;

    /**
     * @param email email
     * @return UserDetailsDto
     */
    @Override
    public UserDetailsDto getUserByEmail(String email) {
        return mapper.map(userService.findUserByEmail(email), UserDetailsDto.class);
    }
}
