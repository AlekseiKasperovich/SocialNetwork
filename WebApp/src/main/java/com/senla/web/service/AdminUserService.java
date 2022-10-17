package com.senla.web.service;

import com.senla.web.dto.user.DtoUser;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface AdminUserService {

    void blockUser(UUID userId);

    void unblockUser(UUID userId);

    Page<DtoUser> getBlockedUsers();
}
