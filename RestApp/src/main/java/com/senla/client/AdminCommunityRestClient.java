package com.senla.client;

import com.senla.dto.community.CommunityDto;
import com.senla.dto.community.CreateCommunityDto;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface AdminCommunityRestClient {

    CommunityDto createCommunity(CreateCommunityDto createCommunityDto);

    CommunityDto updateCommunity(UUID id, CreateCommunityDto createCommunityDto);

    void deleteCommunity(UUID id);
}
