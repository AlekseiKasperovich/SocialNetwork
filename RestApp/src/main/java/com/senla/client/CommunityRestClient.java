package com.senla.client;

import com.senla.dto.community.CommunityDto;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Aliaksei Kaspiarovich */
public interface CommunityRestClient {

    CommunityDto getCommunityById(UUID communityId);

    CommunityDto addUser(UUID communityId);

    CommunityDto deleteUser(UUID communityId);

    Page<CommunityDto> findAll(Pageable pageable, HttpServletRequest request);
}
