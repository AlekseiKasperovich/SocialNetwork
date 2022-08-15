package com.senla.repository;

import com.senla.model.Community;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** @author Aliaksei Kaspiarovich */
@Repository
public interface CommunityRepository extends JpaRepository<Community, UUID> {}
