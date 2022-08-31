package com.senla.controller.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.senla.service.CustomUserService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

class UserControllerTest extends AbstractMockControllerTest {

    @Autowired private CustomUserService customUserService;

    @Test
    void givenExistingId_whenFindingById_thenReturnUser() throws Exception {
        String email = "user@gmail.com";
        UUID id = customUserService.findUserByEmail(email).getId();
        mockMvc.perform(get("/api/users/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.email").value(email))
                .andDo(print());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(get("/api/users/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
