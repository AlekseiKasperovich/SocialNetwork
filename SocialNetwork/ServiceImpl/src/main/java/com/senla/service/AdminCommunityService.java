package com.senla.service;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface AdminCommunityService {

    CommunityDto createCommunity(CreateCommunityDto createCommunityDto, Long id);

    CommunityDto updateCommunity(Long id, CreateCommunityDto createCommunityDto);

    void deleteCommunity(Long id);
}
