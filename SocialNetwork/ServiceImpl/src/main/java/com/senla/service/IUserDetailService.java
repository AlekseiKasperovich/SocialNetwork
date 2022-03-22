package com.senla.service;

import com.senla.api.dto.user.UserDetailsDto;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface IUserDetailService {

    UserDetailsDto getUserByEmail(String email);
}
