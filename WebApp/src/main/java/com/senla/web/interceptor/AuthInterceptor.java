package com.senla.web.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //        if (requestTemplate.url().equals("login") ||
        // requestTemplate.url().equals("login/save") || requestTemplate.url().equals("profile")) {
        //            //don't add global header for the specific url
        //            return;
        //        }
        //        System.out.println("AuthInterceptor before");
        //
        ////        if (SecurityUtil.isAuthenticated()) {
        //        CurrentUserDetails currentUserDetails = SecurityUtil.getCurrentUser();
        //        String token = currentUserDetails.getPassword();
        //        System.out.println(token);
        //        System.out.println("AuthInterceptor after");
        //        requestTemplate.header("Authorization", token);
        requestTemplate.header("Content-Type", "application/json");
        requestTemplate.header("Accept", "application/json");
        ////        }
        ////        return;
    }
}
