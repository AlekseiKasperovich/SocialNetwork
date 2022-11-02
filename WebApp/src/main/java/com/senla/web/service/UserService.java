package com.senla.web.service;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.dto.user.SearchUserDto;
import org.springframework.data.domain.Page;

public interface UserService {

    DtoUser getUser();

    Page<DtoUser> getUsers();

    Page<DtoUser> getUsers(SearchUserDto searchUserDto);
}
