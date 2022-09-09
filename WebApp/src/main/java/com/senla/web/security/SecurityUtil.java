package com.senla.web.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    public static final List<GrantedAuthority> AUTHORITIES =
            List.of(new SimpleGrantedAuthority("ROLE_USER"));

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    public static CurrentUserDetails getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return (CurrentUserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        throw new RuntimeException();
    }
}
