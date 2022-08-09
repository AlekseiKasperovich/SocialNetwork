package com.senla.service.impl;

import com.senla.dto.user.DtoUser;
import com.senla.dto.constants.Roles;
import com.senla.dto.constants.Status;
import com.senla.mapper.Mapper;
import com.senla.model.User;
import com.senla.service.AdminUserService;
import com.senla.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aliaksei Kaspiarovich
 */
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
    public DtoUser blockUser(Long id) {
        User user = userService.findUserById(id);
        if (Roles.ROLE_USER.equals(user.getRole().getName())) {
            user.setStatus(Status.BANNED);
            return mapper.map(userService.save(user), DtoUser.class);
        }
        return mapper.map(user, DtoUser.class);
    }

    /**
     * @param id user ID
     * @return unblocked user
     */
    @Override
    public DtoUser unblockUser(Long id) {
        User user = userService.findUserById(id);
        user.setStatus(Status.ACTIVE);
        return mapper.map(userService.save(user), DtoUser.class);
    }
}
