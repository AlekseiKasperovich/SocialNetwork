package com.senla.service;

import com.senla.model.Friendship;
import java.util.UUID;

/** @author Aliaksei Kaspiarovich */
public interface CustomFriendshipService {

    Friendship findFriendshipById(UUID id);

    boolean checkFriendship(UUID userId, UUID friendId);
}
