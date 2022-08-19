package com.senla.service.custom;

import com.senla.exception.CommunityNotFoundException;
import com.senla.model.Community;
import com.senla.service.CustomCommunityService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

class CustomCommunityServiceTest extends AbstractIntegrationTest {

    @Autowired private CustomCommunityService customCommunityService;

    @Test
    void givenExistingId_whenFindingById_thenReturnCommunity() {
        Community community =
                Community.builder().name("Community").description("Description").build();
        Community saveCommunity = customCommunityService.save(community);

        Community foundCommunity = customCommunityService.findCommunityById(saveCommunity.getId());

        Assertions.assertNotNull(foundCommunity);
        Assertions.assertEquals(saveCommunity.getId(), foundCommunity.getId());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() {
        Assertions.assertThrows(
                CommunityNotFoundException.class,
                () -> customCommunityService.findCommunityById(UUID.randomUUID()));
    }

    @Test
    void givenCommunity_whenSave_thenReturnCommunity() {
        Community community =
                Community.builder().name("Community").description("Description").build();
        Community saveCommunity = customCommunityService.save(community);

        Assertions.assertNotNull(saveCommunity);
        Assertions.assertEquals(community.getName(), saveCommunity.getName());
    }

    @Test
    void givenExistingId_whenDeleteById_thenNotTrowException() {
        Community community =
                Community.builder().name("Community").description("Description").build();
        Community saveCommunity = customCommunityService.save(community);

        Assertions.assertDoesNotThrow(() -> customCommunityService.delete(saveCommunity.getId()));
    }

    @Test
    void givenNonExistingId_whenDeleteById_thenTrowException() {
        Assertions.assertThrows(
                EmptyResultDataAccessException.class,
                () -> customCommunityService.delete(UUID.randomUUID()));
    }
}
