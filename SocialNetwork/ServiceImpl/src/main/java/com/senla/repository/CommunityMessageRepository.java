package com.senla.repository;

import com.senla.model.CommunityMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface CommunityMessageRepository extends
        JpaRepository<CommunityMessage, Long> {

    /**
     *
     * @param communityId community ID
     * @param pageable pagination information
     * @return messages on community
     */
    Page<CommunityMessage> findByCommunityIdOrderByPostedDesc(Long communityId,
            Pageable pageable);

}
