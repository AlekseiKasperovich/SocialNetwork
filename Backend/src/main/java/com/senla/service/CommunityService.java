package com.senla.service;

import com.senla.dto.community.CommunityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface CommunityService {

    CommunityDto getCommunityById(Long id);

    CommunityDto addUser(Long communityId, Long id);

    CommunityDto deleteUser(Long communityId, Long id);

    Page<CommunityDto> findAll(Pageable pageable);
}
