package com.senla.client;

import com.senla.api.dto.user.UserDetailsDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface UserDetailsClient {

    UserDetailsDto findByEmail(String email);

}
