package com.senla.service.custom;

import com.senla.exception.CommunityNotFoundException;
import com.senla.exception.MyAccessDeniedException;
import com.senla.model.Community;
import com.senla.model.User;
import com.senla.repository.CommunityRepository;
import com.senla.service.CustomCommunityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomCommunityServiceImpl implements CustomCommunityService {

    private final CommunityRepository communityRepository;

    /**
     * @param id community ID
     * @return community
     */
    @Transactional(readOnly = true)
    @Override
    public Community findCommunityById(UUID id) {
        return communityRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new CommunityNotFoundException(
                                        String.format("Community with id = %s is not found", id)));
    }

    /**
     * @param community community
     * @return community
     */
    @Override
    public Community save(Community community) {
        return communityRepository.save(community);
    }

    /** @param id community ID */
    @Override
    public void delete(UUID id) {
        communityRepository.deleteById(id);
    }

    /**
     * @param user user
     * @param community community
     */
    @Override
    public void checkUserOnCommunity(User user, Community community) {
        if (!community.getFollowers().contains(user)) {
            throw new MyAccessDeniedException("Access is denied");
        }
    }
}
