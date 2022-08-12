package com.senla.client.impl;

import com.senla.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** @author Aliaksei Kaspiarovich */
public abstract class CurrentUserService {

    protected static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }

    protected static String getCurrentUserId() {
        UserDetailsImpl user =
                (UserDetailsImpl)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.toString(user.getId());
    }
}
