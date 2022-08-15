package com.senla.service;

import com.senla.dto.community.CommunityDto;
import com.senla.dto.community.CreateCommunityDto;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface AdminCommunityService {

    CommunityDto createCommunity(CreateCommunityDto createCommunityDto, UUID id);

    CommunityDto updateCommunity(UUID id, CreateCommunityDto createCommunityDto);

    void deleteCommunity(UUID id);
}
