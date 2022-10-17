package com.senla.web.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private static final List<GrantedAuthority> ANONYMOUS_AUTHORITIES =
            List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

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

    public static List<SimpleGrantedAuthority> mapRoleToAuthorities(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    public static Authentication createAnonymousPrincipal() {
        return new AnonymousAuthenticationToken(
                "Anonymous",
                new CurrentUserDetails(ANONYMOUS_AUTHORITIES, null, null),
                ANONYMOUS_AUTHORITIES);
    }
}
