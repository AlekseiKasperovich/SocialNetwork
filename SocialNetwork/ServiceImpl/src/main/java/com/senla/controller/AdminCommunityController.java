package com.senla.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.service.AdminCommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "api/admin/communities",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api(tags = "Create/update/delete community")
public class AdminCommunityController {

    private final AdminCommunityService adminCommunityService;

    /**
     * @param createCommunityDto community name and description
     * @param email              email
     * @return community
     */
    @ApiOperation(value = "This method is used to create a community.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Community successfully created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public CommunityDto createCommunity(@ApiParam(name = "Community name and description")
                                        @RequestBody CreateCommunityDto createCommunityDto,
                                        @ApiParam(name = "email")
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
