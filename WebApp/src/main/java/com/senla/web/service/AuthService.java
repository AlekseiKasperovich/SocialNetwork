package com.senla.web.service;

import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.DtoUser;

public interface AuthService {

    DtoUser registerNewUserAccount(DtoCreateUser createUserDto);

}
