package com.senla.client;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ForgotPasswordDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface AuthRestClient {

    DtoUser registerNewUserAccount(DtoCreateUser createUserDto);

    void generateNewPassword(ForgotPasswordDto emailDto);

    void resetPassword(ForgotPasswordDto emailDto);
}
