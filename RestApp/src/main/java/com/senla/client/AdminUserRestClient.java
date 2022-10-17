package com.senla.client;

import com.senla.dto.user.DtoUser;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface AdminUserRestClient {

    DtoUser blockUser(UUID id);

    DtoUser unblockUser(UUID id);

    Page<DtoUser> getBannedUsers(Pageable pageable, HttpServletRequest request);
}
