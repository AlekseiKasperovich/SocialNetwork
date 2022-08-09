package com.senla.client;

import com.senla.dto.community.CommunityDto;
import com.senla.dto.community.CreateCommunityDto;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface AdminCommunityRestClient {

    CommunityDto createCommunity(CreateCommunityDto createCommunityDto);

    CommunityDto updateCommunity(Long id, CreateCommunityDto createCommunityDto);

    void deleteCommunity(Long id);

}
