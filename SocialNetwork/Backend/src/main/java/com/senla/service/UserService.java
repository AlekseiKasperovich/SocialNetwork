package com.senla.service;

import com.senla.dto.user.DtoUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface UserService {

    DtoUser getUserById(Long id);

    Page<DtoUser> searchUsers(String firstName, String lastName, Pageable pageable);

}
