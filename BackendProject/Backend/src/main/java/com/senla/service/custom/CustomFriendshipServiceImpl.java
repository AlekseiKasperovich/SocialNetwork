package com.senla.service.custom;

import com.senla.exception.FriendshipNotFoundException;
import com.senla.model.Friendship;
import com.senla.repository.FriendshipRepository;
import com.senla.service.CustomFriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomFriendshipServiceImpl implements CustomFriendshipService {

    private final FriendshipRepository friendshipRepository;

    /**
     * @param id friendship ID
     * @return friendship
     */
    @Override
    public Friendship findFriendshipById(Long id) {
        return friendshipRepository.findById(id).orElseThrow(
                () -> new FriendshipNotFoundException(
                        String.format("Friendship with id = %s is not found", id)));
    }

    /**
     * @param userId   user ID
     * @param friendId other user ID
     * @return {@literal true} if users are friends, {@literal false} otherwise.
     */
    @Override
    public boolean checkFriendship(Long userId, Long friendId) {
        return friendshipRepository.findFriendship(userId, friendId).isPresent();
    }
}
