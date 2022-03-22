package com.senla.client;

import com.senla.api.dto.community.CommunityDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CommunityRestClient {

    CommunityDto getCommunityById(Long communityId);

    CommunityDto addUser(Long communityId);

    CommunityDto deleteUser(Long communityId);

    Page<CommunityDto> findAll(Pageable pageable, HttpServletRequest request);

}
