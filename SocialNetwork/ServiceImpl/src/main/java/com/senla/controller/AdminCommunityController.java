package com.senla.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.service.AdminCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/${application.rest-api.prefix}/admin/communities", // TODO Можно часть перфикса который ен меняется вынести в проперти
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminCommunityController {

    private final AdminCommunityService adminCommunityService;

    /**
     * @param createCommunityDto community name and description
     * @param email              email
     * @return community
     */
    //TODO описать аннотициями только этот контроллер чисто посмотреть как это происходит и делается
    @PostMapping
    public CommunityDto createCommunity(@RequestBody CreateCommunityDto createCommunityDto,
                                        @RequestHeader("${request.email}") String email) {
        return adminCommunityService.createCommunity(createCommunityDto, email);
    }

    /**
     * @param id                 community ID
     * @param createCommunityDto community name and description
     * @return updated community
     */
    @PutMapping("{id}")
    public CommunityDto updateCommunity(@PathVariable Long id,
                                        @RequestBody CreateCommunityDto createCommunityDto) {
        return adminCommunityService.updateCommunity(id, createCommunityDto);
    }

    /**
     * @param id community ID
     */
    @DeleteMapping("{id}")
    public void deleteCommunity(@PathVariable Long id) {
        adminCommunityService.deleteCommunity(id);
    }
}
