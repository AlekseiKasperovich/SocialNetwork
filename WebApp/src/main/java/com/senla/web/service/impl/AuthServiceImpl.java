package com.senla.web.service.impl;

import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.UserAlreadyExistException;
import com.senla.web.feign.AuthClient;
import com.senla.web.service.AuthService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    @Override
    public DtoUser registerNewUserAccount(DtoCreateUser createUserDto) {
        try {
            return authClient.createUser(createUserDto).getBody();
        } catch (FeignException.Conflict ex) {
            throw new UserAlreadyExistException(String.format(
                    "Registration failed! There is an account with that email address: = %s", createUserDto.getEmail()));
        }
    }
}
