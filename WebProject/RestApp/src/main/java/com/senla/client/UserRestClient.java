package com.senla.client;

import com.senla.dto.user.DtoUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface UserRestClient {

    DtoUser getUserById(Long id);

    Page<DtoUser> searchUsers(Pageable pageable, HttpServletRequest request);

}
