package com.senla.impl.service.custom;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public abstract class AbstractService {

    /**
     *
     * @return the <code>email</code> of current authenticated user or <code>null</code> if no authentication
     */
    protected static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }

}
