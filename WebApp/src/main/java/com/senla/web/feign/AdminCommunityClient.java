package com.senla.web.feign;

import com.senla.web.dto.community.CreateCommunityDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "AdminCommunityClient", url = "${request.host}" + "/admin/communities")
public interface AdminCommunityClient {

    @PostMapping
    void createCommunity(CreateCommunityDto createCommunityDto);

    @PutMapping("{communityId}")
    void updateCommunity(@PathVariable UUID communityId, CreateCommunityDto createCommunityDto);

    @DeleteMapping("{communityId}")
    void deleteCommunity(@PathVariable UUID communityId);
}
