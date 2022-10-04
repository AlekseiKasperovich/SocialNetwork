package com.senla.web.service;

import com.senla.web.dto.user.DtoUser;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<DtoUser> getUsers();
}
