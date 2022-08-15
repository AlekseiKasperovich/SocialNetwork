package com.senla.service;

import com.senla.dto.user.DtoUser;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface AdminUserService {

    DtoUser blockUser(UUID id);

    DtoUser unblockUser(UUID id);
}
