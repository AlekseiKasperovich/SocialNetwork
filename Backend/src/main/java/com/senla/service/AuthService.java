package com.senla.service;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ResetPasswordDto;

/** @author Aliaksei Kaspiarovich */
public interface AuthService {

    DtoUser registerNewUserAccount(DtoCreateUser createUserDto);

    void generatePassword(String email);

    void resetPassword(String email, String token);

    void changePassword(ResetPasswordDto resetPasswordDto);
}
