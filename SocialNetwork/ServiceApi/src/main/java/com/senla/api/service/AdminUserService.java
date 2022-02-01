package com.senla.api.service;

import com.senla.api.dto.user.DtoUser;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface AdminUserService {

    DtoUser blockUser(Long id);

    DtoUser unblockUser(Long id);
}
