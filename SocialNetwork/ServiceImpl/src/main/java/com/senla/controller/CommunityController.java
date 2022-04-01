package com.senla.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/communities",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    /**
     * @param communityId community ID
     * @return community
     */
    @GetMapping("{communityId}")
    public CommunityDto getCommunityById(@PathVariable Long communityId) {
        return communityService.getCommunityById(communityId);
    }

    /**
     * @param communityId community ID
     * @param email
     * @return community
     */
    @PutMapping("{communityId}")
    public CommunityDto joinToCommunity(@PathVariable Long communityId,
                                        @RequestHeader("${request.email}") String email) {
        return communityService.addUser(communityId, email);
    }

    /**
     * @param communityId community ID
     * @param email
     * @return community
     */
    @DeleteMapping("{communityId}")
    public CommunityDto leaveCommunity(@PathVariable Long communityId,
                                       @RequestHeader("${request.email}") String email) {
        return communityService.deleteUser(communityId, email);
    }

    /**
     * @param pageable pagination information
     * @return communities
     */
    @GetMapping
    public Page<CommunityDto> findAllCommunities(Pageable pageable) {
        return communityService.findAll(pageable);
    }
}
