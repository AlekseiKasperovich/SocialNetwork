package com.senla.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.client.AdminCommunityRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/admin/communities",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminCommunityController {

    private final AdminCommunityRestClient adminCommunityRestClient;

    /**
     * @param createCommunityDto community name and description
     * @return community
     */
    @PostMapping
    public CommunityDto createCommunity(
            @Valid @RequestBody CreateCommunityDto createCommunityDto) {
        return adminCommunityRestClient.createCommunity(createCommunityDto);
    }

    /**
     * @param id                 community ID
     * @param createCommunityDto community name and description
     * @return updated community
     */
    @PutMapping("{id}")
    public CommunityDto updateCommunity(@PathVariable Long id,
                                        @Valid @RequestBody CreateCommunityDto createCommunityDto) {
        return adminCommunityRestClient.updateCommunity(id, createCommunityDto);
    }

    /**
     * @param id community ID
     */
    @DeleteMapping("{id}")
    public void deleteCommunity(@PathVariable Long id) {
        adminCommunityRestClient.deleteCommunity(id);
    }
}
