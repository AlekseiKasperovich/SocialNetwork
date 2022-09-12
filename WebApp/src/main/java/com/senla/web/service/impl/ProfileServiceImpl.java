package com.senla.web.service.impl;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.feign.ProfileClient;
import com.senla.web.security.CurrentUserDetails;
import com.senla.web.security.SecurityUtil;
import com.senla.web.service.ProfileService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileClient profileClient;

    @Override
    public DtoUser getCurrentUserProfile() {
        try {
            String token = null;
            if (SecurityUtil.isAuthenticated()) {
                CurrentUserDetails currentUserDetails = SecurityUtil.getCurrentUser();
                token = currentUserDetails.getPassword();
                System.out.println(token);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", token);
                //                headerMap.put("Authorization", token);
                //                headerMap.put("Content-Type", "application/json");
                //                headerMap.put("Accept", "application/json");
            }
            return profileClient.getProfile(token).getBody();
        } catch (FeignException.Forbidden ex) {
            throw new MyAccessDeniedException("something went wrong");
        }
    }
}
