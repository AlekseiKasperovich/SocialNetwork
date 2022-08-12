package com.senla.service;

import com.senla.dto.user.DtoUser;

/** @author Aliaksei Kaspiarovich */
public interface AdminUserService {

    DtoUser blockUser(Long id);

    DtoUser unblockUser(Long id);
}
