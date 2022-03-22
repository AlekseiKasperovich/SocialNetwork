package com.senla.service;

import com.senla.model.Friendship;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface CustomFriendshipService {

    Friendship findFriendshipById(Long id);

    boolean checkFriendship(Long userId, Long friendId);
}
