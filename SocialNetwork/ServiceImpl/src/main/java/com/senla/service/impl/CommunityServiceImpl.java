package com.senla.service.impl;

import com.senla.api.dto.community.CommunityDto;
import com.senla.mapper.Mapper;
import com.senla.model.Community;
import com.senla.model.User;
import com.senla.repository.CommunityRepository;
import com.senla.service.CommunityService;
import com.senla.service.CustomCommunityService;
import com.senla.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final CustomUserService userService;
    private final CommunityRepository communityRepository;
    private final CustomCommunityService communityService;
    private final Mapper mapper;

    /**
     * @param id community ID
     * @return community
     */
    @Override
    public CommunityDto getCommunityById(Long id) {
        return mapper.map(communityService.findCommunityById(id), CommunityDto.class);
    }

    /**
     * @param communityId community ID
     * @param id id
     * @return community
     */
    @Override
    public CommunityDto addUser(Long communityId, Long id) {
        Community community = communityService.findCommunityById(communityId);
        User user = userService.findUserById(id);
        if (community.getFollowers().add(user)) {
            return mapper.map(communityService.save(community), CommunityDto.class);
        } else {
            return mapper.map(community, CommunityDto.class);
        }
    }

    /**
     * @param communityId community ID
     * @param id id
     * @return community
     */
    @Override
    public CommunityDto deleteUser(Long communityId, Long id) {
        Community community = communityService.findCommunityById(communityId);
        User user = userService.findUserById(id);
        if (community.getFollowers().remove(user)) {
            return mapper.map(communityService.save(community), CommunityDto.class);
        } else {
            return mapper.map(community, CommunityDto.class);
        }
    }

    /**
     * @param pageable pagination information
     * @return communities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommunityDto> findAll(Pageable pageable) {
        Page<Community> communityPage = communityRepository.findAll(pageable);
        return communityPage.map(community -> mapper.map(community, CommunityDto.class));
    }

}
