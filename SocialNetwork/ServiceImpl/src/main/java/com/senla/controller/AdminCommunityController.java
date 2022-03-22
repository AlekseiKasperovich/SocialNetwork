package com.senla.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.api.dto.сonstants.Constants;
import com.senla.service.AdminCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/admin/communities",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminCommunityController {

    private final AdminCommunityService adminCommunityService;

    /**
     *
     * @param createCommunityDto community name and description
     * @param email
     * @return community
     */
    @PostMapping
    public CommunityDto createCommunity(@RequestBody CreateCommunityDto createCommunityDto,
            @RequestHeader(Constants.EMAIL_HEADER) String email) {
        return adminCommunityService.createCommunity(createCommunityDto, email);
    }

    /**
     *
     * @param id community ID
     * @param createCommunityDto community name and description
     * @return updated community
     */
    @PutMapping("{id}")
    public CommunityDto updateCommunity(@PathVariable Long id,
            @RequestBody CreateCommunityDto createCommunityDto) {
        return adminCommunityService.updateCommunity(id, createCommunityDto);
    }

    /**
     *
     * @param id community ID
     */
    @DeleteMapping("{id}")
    public void deleteCommunity(@PathVariable Long id) {
        adminCommunityService.deleteCommunity(id);
    }
}
