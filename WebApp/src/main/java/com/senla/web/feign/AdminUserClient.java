package com.senla.web.feign;

import com.senla.web.dto.user.DtoUser;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AdminUserClient", url = "${request.host}" + "/admin/users")
public interface AdminUserClient {

    @PatchMapping("{userId}/block")
    void blockUser(@PathVariable UUID userId);

    @PatchMapping("{userId}/unblock")
    void unblockUser(@PathVariable UUID userId);

    @GetMapping("banned")
    ResponseEntity<Page<DtoUser>> getBlockedUsers(Pageable page);
}
