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
//    protected static String getCurrentUserEmail() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication == null ? null : authentication.getName();
//    }
//
//    protected static UUID getCurrentUserId() {
//        UserDetailsImpl user =
//                (UserDetailsImpl)
//                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return user.getId();
//    }
