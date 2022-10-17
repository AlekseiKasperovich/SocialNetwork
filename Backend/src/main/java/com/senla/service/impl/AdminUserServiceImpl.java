package com.senla.service.impl;

import com.senla.dto.constants.Roles;
import com.senla.dto.constants.Status;
import com.senla.dto.user.DtoUser;
import com.senla.exception.MyAccessDeniedException;
import com.senla.mapper.Mapper;
import com.senla.model.User;
import com.senla.service.AdminUserService;
import com.senla.service.CustomUserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    private final Mapper mapper;
    private final CustomUserService userService;

    /**
     * @param id user ID
     * @return blocked user
     */
    @Override
    public DtoUser blockUser(UUID id) {
        User user = userService.findUserById(id);
        if (Roles.ROLE_USER.equals(user.getRole().getName())) {
            user.setStatus(Status.BANNED);
            return mapper.map(userService.save(user), DtoUser.class);
        }
        throw new MyAccessDeniedException("You cannot block a user");
    }

    /**
     * @param id user ID
     * @return unblocked user
     */
    @Override
    public DtoUser unblockUser(UUID id) {
        User user = userService.findUserById(id);
        user.setStatus(Status.ACTIVE);
        return mapper.map(userService.save(user), DtoUser.class);
    }

    @Override
    public Page<DtoUser> getBannedUsers(Pageable pageable) {
        Page<User> userPage = userService.findBannedUsers(pageable);
        return userPage.map(user -> mapper.map(user, DtoUser.class));
    }
}
