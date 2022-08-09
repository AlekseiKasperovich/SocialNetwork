package com.senla.service;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ForgotPasswordDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface AuthService {

    DtoUser registerNewUserAccount(DtoCreateUser createUserDto);

    void sendNewPassword(ForgotPasswordDto emailDto);

}
