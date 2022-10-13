package com.senla.web.service;

import com.senla.web.dto.community.CreateCommunityDto;
import com.senla.web.dto.community.UpdateCommunityDto;
import java.util.UUID;

public interface AdminCommunityService {

    void createCommunity(CreateCommunityDto createCommunityDto);

    void updateCommunity(UpdateCommunityDto updateCommunityDto);

    void deleteCommunity(UUID communityId);
}
