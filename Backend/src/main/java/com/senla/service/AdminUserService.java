package com.senla.service;

import com.senla.dto.user.DtoUser;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface AdminUserService {

    DtoUser blockUser(UUID id);

    DtoUser unblockUser(UUID id);

    Page<DtoUser> getBannedUsers(Pageable pageable);
}
