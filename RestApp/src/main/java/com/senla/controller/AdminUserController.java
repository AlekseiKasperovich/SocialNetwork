package com.senla.controller;

import com.senla.client.AdminUserRestClient;
import com.senla.dto.user.DtoUser;
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

    private final AdminUserRestClient adminUseRestClient;

    /**
     * @param id user ID
     * @return blocked user
     */
    @PatchMapping("{id}/block")
    public DtoUser blockUser(@PathVariable Long id) {
        return adminUseRestClient.blockUser(id);
    }

    /**
     * @param id user ID
     * @return unblocked user
     */
    @PatchMapping("{id}/unblock")
    public DtoUser unblockUser(@PathVariable Long id) {
        return adminUseRestClient.unblockUser(id);
    }
}
