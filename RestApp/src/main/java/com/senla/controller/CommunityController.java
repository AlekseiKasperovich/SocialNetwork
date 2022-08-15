package com.senla.controller;

import com.senla.client.CommunityRestClient;
import com.senla.dto.community.CommunityDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequestMapping(
        value = "/api/communities",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityRestClient communityRestClient;

    /**
     * @param communityId community ID
     * @return community
     */
    @GetMapping("{communityId}")
    public CommunityDto getCommunityById(@PathVariable UUID communityId) {
        return communityRestClient.getCommunityById(communityId);
    }

    /**
     * @param communityId community ID
     * @return community
     */
    @PutMapping("{communityId}")
    public CommunityDto joinToCommunity(@PathVariable UUID communityId) {
        return communityRestClient.addUser(communityId);
    }

    /**
     * @param communityId community ID
     * @return community
     */
    @DeleteMapping("{communityId}")
    public CommunityDto leaveCommunity(@PathVariable UUID communityId) {
        return communityRestClient.deleteUser(communityId);
    }

    /**
     * @param pageable pagination information
     * @param request request
     * @return communities
     */
    @GetMapping
    public Page<CommunityDto> findAllCommunities(Pageable pageable, HttpServletRequest request) {
        return communityRestClient.findAll(pageable, request);
    }
}
