package com.senla.web.feign;

import com.senla.web.dto.community.CommunityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CommunityClient", url = "${request.host}" + "/communities")
public interface CommunityClient {

    @GetMapping
    ResponseEntity<Page<CommunityDto>> getCommunities(Pageable page);
}
