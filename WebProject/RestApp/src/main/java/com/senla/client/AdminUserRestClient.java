package com.senla.client;

import com.senla.api.dto.user.DtoUser;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface AdminUserRestClient {

    DtoUser blockUser(Long id);

    DtoUser unblockUser(Long id);

}
