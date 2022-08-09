package com.senla.client;

import com.senla.dto.user.UserDetailsDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface UserDetailsClient {

    UserDetailsDto findByEmail(String email);

}
