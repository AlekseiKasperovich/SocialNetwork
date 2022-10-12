package com.senla.controller;

import com.senla.client.AdminUserRestClient;
import com.senla.dto.user.DtoUser;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public DtoUser blockUser(@PathVariable UUID id) {
        return adminUseRestClient.blockUser(id);
    }

    /**
     * @param id user ID
     * @return unblocked user
     */
    @PatchMapping("{id}/unblock")
    public DtoUser unblockUser(@PathVariable UUID id) {
        return adminUseRestClient.unblockUser(id);
    }

    @GetMapping("banned")
    public Page<DtoUser> getBannedUsers(Pageable pageable, HttpServletRequest request) {
        return adminUseRestClient.getBannedUsers(pageable, request);
    }
}
