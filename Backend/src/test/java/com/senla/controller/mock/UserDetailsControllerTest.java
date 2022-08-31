package com.senla.controller.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;

class UserDetailsControllerTest extends AbstractMockControllerTest {

    @Test
    void givenExistingEmail_whenFindingByEmail_thenReturnUser() throws Exception {
        String email = "user@gmail.com";
        mockMvc.perform(
                        get("/api/users/details")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("email", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email))
                .andDo(print());
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenStatusNotFound() throws Exception {
        String email = "another@gmail.com";
        mockMvc.perform(
                        get("/api/users/details")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("email", email))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
