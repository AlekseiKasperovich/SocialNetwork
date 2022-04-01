package com.senla.service;

import com.senla.api.dto.user.DtoCreateUser;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.user.ForgotPasswordDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface AuthService {

    DtoUser registerNewUserAccount(DtoCreateUser createUserDto);

    void sendNewPassword(ForgotPasswordDto emailDto);

}
