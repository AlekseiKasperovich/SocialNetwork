package com.senla.repository;

import com.senla.model.Message;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** @author Aliaksei Kaspiarovich */
@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    /**
     * @param user1 user ID
     * @param user2 other user ID
     * @param pageable pagination information
     * @return user messages
     */
    @Query(
            value =
                    "SELECT m FROM Message m WHERE (m.sender.id = :user1 AND m.receiver.id ="
                        + " :user2) OR (m.sender.id = :user2 AND m.receiver.id = :user1) ORDER BY"
                        + " m.posted DESC")
    Page<Message> findMessages(
            @Param("user1") UUID user1, @Param("user2") UUID user2, Pageable pageable);
}
