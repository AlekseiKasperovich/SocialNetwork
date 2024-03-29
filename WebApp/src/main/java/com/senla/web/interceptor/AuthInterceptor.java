package com.senla.web.interceptor;

import com.senla.web.security.CurrentUserDetails;
import com.senla.web.security.SecurityUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (SecurityUtil.isAuthenticated()
                && SecurityContextHolder.getContext().getAuthentication().getClass()
                        != AnonymousAuthenticationToken.class) {
            CurrentUserDetails currentUserDetails = SecurityUtil.getCurrentUser();
            String token = currentUserDetails.getPassword();
            requestTemplate.header("Authorization", token);
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        }
    }
}
