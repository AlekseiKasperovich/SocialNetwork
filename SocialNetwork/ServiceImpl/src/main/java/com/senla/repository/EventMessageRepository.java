package com.senla.repository;

import com.senla.model.EventMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface EventMessageRepository extends JpaRepository<EventMessage, Long> {

    /**
     *
     * @param eventId event ID
     * @param pageable pagination information
     * @return messages on event
     */
    Page<EventMessage> findByEventIdOrderByPostedDesc(Long eventId, Pageable pageable);

}
