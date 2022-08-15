package com.senla.service;

import com.senla.dto.community.CommunityDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface CommunityService {

    CommunityDto getCommunityById(UUID id);

    CommunityDto addUser(UUID communityId, UUID id);

    CommunityDto deleteUser(UUID communityId, UUID id);

    Page<CommunityDto> findAll(Pageable pageable);
}
