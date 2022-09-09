package com.senla.web.service.impl;

import com.senla.web.dto.user.DtoUser;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.feign.ProfileClient;
import com.senla.web.service.ProfileService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileClient profileClient;

    @Override
    public DtoUser getCurrentUserProfile() {
        try {
            return profileClient.getProfile().getBody();
        } catch (FeignException.Forbidden ex) {
            System.out.println(ex.getMessage());
            throw new MyAccessDeniedException("something went wrong");
        }
    }
}
