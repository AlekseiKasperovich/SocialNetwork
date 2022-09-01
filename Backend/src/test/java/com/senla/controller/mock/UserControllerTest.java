package com.senla.controller.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.dto.user.DtoUser;
import com.senla.service.CustomUserService;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

class UserControllerTest extends AbstractMockControllerTest {

    @Autowired private CustomUserService customUserService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void givenExistingId_whenFindingById_thenReturnUser() throws Exception {
        String email = "user@gmail.com";
        UUID id = customUserService.findUserByEmail(email).getId();

        MvcResult mvcResult =
                mockMvc.perform(get("/api/users/{id}", id).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(id, returnedUser.getId());
        Assertions.assertEquals(email, returnedUser.getEmail());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/api/users/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
