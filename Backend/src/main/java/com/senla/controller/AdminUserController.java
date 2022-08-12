package com.senla.controller;

import com.senla.dto.user.DtoUser;
import com.senla.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/admin/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    /**
     * @param id user ID
     * @return blocked user
     */
    @PatchMapping("{id}/block")
    public DtoUser blockUser(@PathVariable Long id) {
        return adminUserService.blockUser(id);
    }

    /**
     * @param id user ID
     * @return unblocked user
     */
    @PatchMapping("{id}/unblock")
    public DtoUser unblockUser(@PathVariable Long id) {
        return adminUserService.unblockUser(id);
    }
}
