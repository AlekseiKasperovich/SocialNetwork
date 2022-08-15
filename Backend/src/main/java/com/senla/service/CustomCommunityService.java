package com.senla.service;

import com.senla.model.Community;
import com.senla.model.User;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface CustomCommunityService {

    Community findCommunityById(UUID id);

    Community save(Community community);

    void delete(UUID id);

    void checkUserOnCommunity(User user, Community community);
}
