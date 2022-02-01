package com.senla.rest.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    /**
     *
     * @param communityId community ID
     * @return community
     */
    @GetMapping("{communityId}")
    public CommunityDto getCommunityById(@PathVariable Long communityId) {
        return communityService.getCommunityById(communityId);
    }

    /**
     *
     * @param communityId community ID
     * @return community
     */
    @PutMapping("{communityId}")
    public CommunityDto joinToCommunity(@PathVariable Long communityId) {
        return communityService.addUser(communityId);
    }

    /**
     *
     * @param communityId community ID
     * @return community
     */
    @DeleteMapping("{communityId}")
    public CommunityDto leaveCommunity(@PathVariable Long communityId) {
        return communityService.deleteUser(communityId);
    }

    /**
     *
     * @param pageable pagination information
     * @return communities
     */
    @GetMapping
    public Page<CommunityDto> findAllCommunities(Pageable pageable) {
        return communityService.findAll(pageable);
    }
}
