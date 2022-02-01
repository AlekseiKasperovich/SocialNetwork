package com.senla.impl.service.custom;

import com.senla.api.exception.CommunityNotFoundException;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.impl.model.Community;
import com.senla.impl.model.User;
import com.senla.impl.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomCommunityService {

    private final CommunityRepository communityRepository;

    /**
     *
     * @param id community ID
     * @return community
     */
    @Transactional(readOnly = true)
    public Community findCommunityById(Long id) {
        return communityRepository.findById(id).orElseThrow(
                () -> new CommunityNotFoundException(
                        String.format("Community with id = %s is not found", id)));
    }

    /**
     *
     * @param community community
     * @return community
     */
    public Community save(Community community) {
        return communityRepository.save(community);
    }

    /**
     *
     * @param id community ID
     */
    public void delete(Long id) {
        communityRepository.deleteById(id);
    }

    /**
     *
     * @param user user 
     * @param community community
     */
    public void checkUserOnCommunity(User user, Community community) {
        if (!community.getFollowers().contains(user)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }

}
