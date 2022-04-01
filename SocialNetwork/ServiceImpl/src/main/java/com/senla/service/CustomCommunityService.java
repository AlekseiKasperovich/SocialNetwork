package com.senla.service;

import com.senla.model.Community;
import com.senla.model.User;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface CustomCommunityService {

    Community findCommunityById(Long id);

    Community save(Community community);

    void delete(Long id);

    void checkUserOnCommunity(User user, Community community);

}
