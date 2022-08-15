package com.senla.client.impl;

import com.senla.security.UserDetailsImpl;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/** @author Aliaksei Kaspiarovich */
public abstract class CurrentUserService {

    protected static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }

    protected static UUID getCurrentUserId() {
        UserDetailsImpl user =
                (UserDetailsImpl)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
