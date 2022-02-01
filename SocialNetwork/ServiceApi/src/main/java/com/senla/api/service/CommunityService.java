package com.senla.api.service;

import com.senla.api.dto.community.CommunityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityService {

    CommunityDto getCommunityById(Long id);

    CommunityDto addUser(Long communityId);

    CommunityDto deleteUser(Long communityId);

    Page<CommunityDto> findAll(Pageable pageable);
}
