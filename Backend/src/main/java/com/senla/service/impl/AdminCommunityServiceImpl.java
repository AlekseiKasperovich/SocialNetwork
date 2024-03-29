package com.senla.service.impl;

import com.senla.dto.community.CommunityDto;
import com.senla.dto.community.CreateCommunityDto;
import com.senla.mapper.Mapper;
import com.senla.model.Community;
import com.senla.service.AdminCommunityService;
import com.senla.service.CustomCommunityService;
import com.senla.service.CustomUserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminCommunityServiceImpl implements AdminCommunityService {

    private final Mapper mapper;
    private final CustomCommunityService communityService;
    private final CustomUserService userService;

    /**
     * @param createCommunityDto community name and description
     * @param id id
     * @return community
     */
    @Override
    public CommunityDto createCommunity(CreateCommunityDto createCommunityDto, UUID id) {
        Community community = mapper.map(createCommunityDto, Community.class);
        community.getFollowers().add(userService.findUserById(id));
        return mapper.map(communityService.save(community), CommunityDto.class);
    }

    /**
     * @param id community ID
     * @param createCommunityDto community name and description
     * @return updated community
     */
    @Override
    public CommunityDto updateCommunity(UUID id, CreateCommunityDto createCommunityDto) {
        Community community = communityService.findCommunityById(id);
        mapper.map(createCommunityDto, community);
        return mapper.map(communityService.save(community), CommunityDto.class);
    }

    /** @param id community ID */
    @Override
    public void deleteCommunity(UUID id) {
        communityService.findCommunityById(id);
        communityService.delete(id);
    }
}
