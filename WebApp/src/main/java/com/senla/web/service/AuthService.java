package com.senla.web.service;

import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.LoginUserDto;

public interface AuthService {

    void registerNewUserAccount(DtoCreateUser createUserDto);

    TokenDto login(LoginUserDto loginUserDto);
}
