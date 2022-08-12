package com.senla.client;

import com.senla.dto.user.DtoUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface UserRestClient {

    DtoUser getUserById(Long id);

    Page<DtoUser> searchUsers(Pageable pageable, HttpServletRequest request);
}
