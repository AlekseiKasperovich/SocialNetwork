package com.senla.repository;

import com.senla.model.Friendship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    /**
     * @param authorId user ID
     * @param friendId other user ID
     * @return user friendship
     */
    @Query("SELECT f FROM Friendship f WHERE (f.sender.id = :authorId AND f.receiver.id = :friendId AND f.accepted = true) "
            + "OR (f.sender.id = :friendId AND f.receiver.id = :authorId AND f.accepted = true)")
    Optional<Friendship> findFriendship(@Param("authorId") Long authorId, @Param("friendId") Long friendId);

    /**
     * @param userId   user ID
     * @param friendId other user ID
     * @return user friendship request
     */
    @Query("SELECT f FROM Friendship f WHERE (f.sender.id = :userId AND f.receiver.id = :friendId AND f.accepted = false) "
            + "OR (f.sender.id = :friendId AND f.receiver.id = :userId AND f.accepted = false)")
    Optional<Friendship> findFriendshipRequest(@Param("userId") Long userId, @Param("friendId") Long friendId);

    /**
     * @param userId   user ID
     * @param pageable pagination information
     * @return user friendships
     */
    @Query("SELECT f FROM Friendship f WHERE (f.sender.id = :userId OR f.receiver.id = :userId) "
            + "AND f.accepted = true")
    Page<Friendship> findMyFriends(@Param("userId") Long userId, Pageable pageable);


    /**
     * @param userId   user ID
     * @param pageable pagination information
     * @return friend request list
     */
    @Query("SELECT f FROM Friendship f WHERE (f.receiver.id = :userId AND f.accepted = false)")
    Page<Friendship> getMyFriendshipRequests(@Param("userId") Long userId, Pageable pageable);


}
