package com.senla.web.service.impl;

import com.senla.web.dto.community.CommunityDto;
import com.senla.web.feign.CommunityClient;
import com.senla.web.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityClient communityClient;

    @Override
    public Page<CommunityDto> getCommunities() {
        Pageable page = PageRequest.of(0, 20);
        return communityClient.getCommunities(page).getBody();
    }
}
