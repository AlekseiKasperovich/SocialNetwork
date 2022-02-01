package com.senla.rest.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.api.service.AdminCommunityService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping("/api/admin/communities")
@RequiredArgsConstructor
public class AdminCommunityController {

    private final AdminCommunityService adminCommunityService;

    /**
     *
     * @param createCommunityDto community name and description
     * @return community
     */
    @PostMapping
    public CommunityDto createCommunity(
            @Valid @RequestBody CreateCommunityDto createCommunityDto) {
        return adminCommunityService.createCommunity(createCommunityDto);
    }

    /**
     *
     * @param id community ID
     * @param createCommunityDto community name and description
     * @return updated community
     */
    @PutMapping("{id}")
    public CommunityDto updateCommunity(@PathVariable Long id,
            @Valid @RequestBody CreateCommunityDto createCommunityDto) {
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
