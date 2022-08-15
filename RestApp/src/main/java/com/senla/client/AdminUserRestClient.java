package com.senla.client;

import com.senla.dto.user.DtoUser;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface AdminUserRestClient {

    DtoUser blockUser(UUID id);

    DtoUser unblockUser(UUID id);
}
