package com.senla.impl.repository;

import com.senla.impl.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}
