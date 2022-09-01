package com.senla.controller.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ForgotPasswordDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MvcResult;

class AuthControllerTest extends AbstractMockControllerTest {

    @Autowired private ObjectMapper objectMapper;

    @Test
    void givenUser_whenRegister_thenReturnUser() throws Exception {
        DtoCreateUser dtoCreateUser =
                DtoCreateUser.builder()
                        .email("email@gmail.com")
                        .password("password")
                        .matchingPassword("password")
                        .build();

        MvcResult mvcResult =
                mockMvc.perform(
                                post("/api/auth/registration")
                                        .content(objectMapper.writeValueAsString(dtoCreateUser))
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(dtoCreateUser.getEmail(), returnedUser.getEmail());
    }

    @Test
    void givenExistingEmail_whenRegister_thenStatusConflict() throws Exception {
        DtoCreateUser dtoCreateUser =
                DtoCreateUser.builder()
                        .email("user@gmail.com")
                        .password("password")
                        .matchingPassword("password")
                        .build();

        mockMvc.perform(
                        post("/api/auth/registration")
                                .content(objectMapper.writeValueAsString(dtoCreateUser))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void givenExistingEmail_whenSendPassword_thenSaveNewPassword() throws Exception {
        String email = "user@gmail.com";
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto(email);

        mockMvc.perform(
                        post("/api/auth/password/new")
                                .content(objectMapper.writeValueAsString(forgotPasswordDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenNonExistingEmail_whenSendPassword_thenStatusNotFound() throws Exception {
        String email = "another@gmail.com";
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto(email);

        mockMvc.perform(
                        post("/api/auth/password/new")
                                .content(objectMapper.writeValueAsString(forgotPasswordDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
