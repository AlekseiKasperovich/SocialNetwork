package com.senla.controller.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.dto.user.DtoUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MvcResult;

class UserDetailsControllerTest extends AbstractMockControllerTest {

    @Autowired private ObjectMapper objectMapper;

    @Test
    void givenExistingEmail_whenFindingByEmail_thenReturnUser() throws Exception {
        String email = "user@gmail.com";

        MvcResult mvcResult =
                mockMvc.perform(
                                get("/api/users/details")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .header("email", email))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(email, returnedUser.getEmail());
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenStatusNotFound() throws Exception {
        String email = "another@gmail.com";

        mockMvc.perform(
                        get("/api/users/details")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("email", email))
                .andExpect(status().isNotFound());
    }
}
