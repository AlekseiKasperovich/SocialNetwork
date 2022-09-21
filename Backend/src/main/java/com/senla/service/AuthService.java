package com.senla.service;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;

/** @author Aliaksei Kaspiarovich */
public interface AuthService {

    DtoUser registerNewUserAccount(DtoCreateUser createUserDto);

    void generatePassword(String email);

    void resetPassword(String email, String token);
}
