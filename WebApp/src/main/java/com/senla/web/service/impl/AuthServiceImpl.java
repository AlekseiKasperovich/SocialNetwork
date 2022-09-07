package com.senla.web.service.impl;

import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.LoginUserDto;
import com.senla.web.exception.MyAccessDeniedException;
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
    public void registerNewUserAccount(DtoCreateUser createUserDto) {
        try {
            authClient.createUser(createUserDto);
        } catch (FeignException.Conflict ex) {
            throw new UserAlreadyExistException(
                    String.format(
                            "Registration failed! There is an account with that email address = %s",
                            createUserDto.getEmail()));
        }
    }

    @Override
    public TokenDto login(LoginUserDto loginUserDto) {
        try {
            return authClient.login(loginUserDto);
        } catch (FeignException.Unauthorized ex) {
            System.out.println(ex.getMessage());
            throw new MyAccessDeniedException("Your account has been deleted or blocked");
        } catch (FeignException.Forbidden ex) {
            System.out.println(ex.getMessage());
            throw new MyAccessDeniedException("Incorrect email or password");
        }
    }
}
