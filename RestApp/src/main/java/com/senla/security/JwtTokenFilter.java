package com.senla.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** @author Aliaksei Kaspiarovich */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.getTokenFromRequest(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetailsImpl userDetails =
                        UserDetailsImpl.builder()
                                .id(jwtTokenProvider.getIdFromToken(token))
                                .username(jwtTokenProvider.getUsernameFromToken(token))
                                .authorities(jwtTokenProvider.getRoleFromToken(token))
                                .build();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
