package com.senla.api.service;

import com.senla.api.dto.user.CreateUser;
import com.senla.api.dto.user.ForgotPasswordDto;
import com.senla.api.dto.user.DtoUser;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface AuthService {

    DtoUser registerNewUserAccount(CreateUser createUserDto);

    void sendNewPassword(ForgotPasswordDto emailDto);

}
