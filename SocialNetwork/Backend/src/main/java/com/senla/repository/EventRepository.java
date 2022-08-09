package com.senla.repository;

import com.senla.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * @param authorId user ID
     * @param pageable pagination information
     * @return user events
     */
    Page<Event> findByAuthorId(Long authorId, Pageable pageable);

}
