package com.senla.impl.service;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.impl.mapper.Mapper;
import com.senla.impl.model.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.senla.api.service.AdminCommunityService;
import com.senla.impl.service.custom.CustomCommunityService;
import com.senla.impl.service.custom.CustomUserService;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminCommunityServiceImpl implements AdminCommunityService {

    private final Mapper mapper;
    private final CustomCommunityService communityService;
    private final CustomUserService userService;

    /**
     *
     * @param createCommunityDto community name and description
     * @return community
     */
    @Override
    public CommunityDto createCommunity(CreateCommunityDto createCommunityDto) {
        Community community = mapper.map(createCommunityDto, Community.class);
        community.getFollowers().add(userService.getCurrentUser());
        return mapper.map(communityService.save(community), CommunityDto.class);
    }

    /**
     *
     * @param id community ID
     * @param createCommunityDto community name and description
     * @return updated community
     */
    @Override
    public CommunityDto updateCommunity(Long id, CreateCommunityDto createCommunityDto) {
        Community community = communityService.findCommunityById(id);
        mapper.map(createCommunityDto, community);
        return mapper.map(communityService.save(community), CommunityDto.class);
    }

    /**
     *
     * @param id community ID
     */
    @Override
    public void deleteCommunity(Long id) {
        Community community = communityService.findCommunityById(id);
        communityService.delete(id);
    }
}
