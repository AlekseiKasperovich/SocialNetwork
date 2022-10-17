package com.senla.web.service;

import com.senla.web.dto.community.CommunityDto;
import org.springframework.data.domain.Page;

public interface CommunityService {
    Page<CommunityDto> getCommunities();
}
