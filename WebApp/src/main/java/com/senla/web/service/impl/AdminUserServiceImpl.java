package com.senla.web.service.impl;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.feign.AdminUserClient;
import com.senla.web.service.AdminUserService;
import feign.FeignException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserClient adminUserClient;

    @Override
    public void blockUser(UUID userId) {
        try {
            adminUserClient.blockUser(userId);
        } catch (FeignException.Conflict ex) {
            throw new MyAccessDeniedException("You cannot block a user");
        }
    }

    @Override
    public void unblockUser(UUID userId) {
        adminUserClient.unblockUser(userId);
    }

    @Override
    public Page<DtoUser> getBlockedUsers() {
        Pageable page = PageRequest.of(0, 20);
        return adminUserClient.getBlockedUsers(page).getBody();
    }
}
