package com.senla.web.service;

import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.ForgotPasswordDto;
import com.senla.web.dto.user.LoginUserDto;

public interface AuthService {

    void registerNewUserAccount(DtoCreateUser createUserDto);

    void login(LoginUserDto loginUserDto);

    void sendPassword(ForgotPasswordDto forgotPasswordDto);
}
