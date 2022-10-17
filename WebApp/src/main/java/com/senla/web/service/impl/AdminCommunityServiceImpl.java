package com.senla.web.service.impl;

import com.senla.web.dto.community.CreateCommunityDto;
import com.senla.web.dto.community.UpdateCommunityDto;
import com.senla.web.feign.AdminCommunityClient;
import com.senla.web.service.AdminCommunityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCommunityServiceImpl implements AdminCommunityService {

    private final AdminCommunityClient adminCommunityClient;

    @Override
    public void createCommunity(CreateCommunityDto createCommunityDto) {
        adminCommunityClient.createCommunity(createCommunityDto);
    }

    @Override
    public void updateCommunity(UpdateCommunityDto updateCommunityDto) {
        CreateCommunityDto createCommunityDto =
                new CreateCommunityDto(
                        updateCommunityDto.getName(), updateCommunityDto.getDescription());
        adminCommunityClient.updateCommunity(updateCommunityDto.getId(), createCommunityDto);
    }

    @Override
    public void deleteCommunity(UUID communityId) {
        adminCommunityClient.deleteCommunity(communityId);
    }
}
