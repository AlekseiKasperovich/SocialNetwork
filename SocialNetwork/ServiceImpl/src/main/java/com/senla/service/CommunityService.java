package com.senla.service;

import com.senla.api.dto.community.CommunityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityService {

    CommunityDto getCommunityById(Long id);

    CommunityDto addUser(Long communityId, String email);

    CommunityDto deleteUser(Long communityId, String email);

    Page<CommunityDto> findAll(Pageable pageable);
}
