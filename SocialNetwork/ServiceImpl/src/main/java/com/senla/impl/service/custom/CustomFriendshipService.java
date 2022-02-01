package com.senla.impl.service.custom;

import com.senla.api.exception.FriendshipNotFoundException;
import com.senla.impl.model.Friendship;
import com.senla.impl.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomFriendshipService {

    private final FriendshipRepository friendshipRepository;

    /**
     *
     * @param id friendship ID
     * @return friendship
     */
    public Friendship findFriendshipById(Long id) {
        return friendshipRepository.findById(id).orElseThrow(
                () -> new FriendshipNotFoundException(
                        String.format("Friendship with id = %s is not found", id)));
    }

    /**
     *
     * @param userId user ID
     * @param friendId other user ID
     * @return {@literal true} if users are friends, {@literal false} otherwise.
     */
    public boolean checkFriendship(Long userId, Long friendId) {
        return friendshipRepository.findFriendship(userId, friendId).isPresent();
    }
}
