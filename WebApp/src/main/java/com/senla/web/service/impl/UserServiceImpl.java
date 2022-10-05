package com.senla.web.service.impl;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.feign.UserClient;
import com.senla.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public Page<DtoUser> getUsers() {
        Pageable page = PageRequest.of(0, 20);
        return userClient.getUsers(page).getBody();
    }
}
