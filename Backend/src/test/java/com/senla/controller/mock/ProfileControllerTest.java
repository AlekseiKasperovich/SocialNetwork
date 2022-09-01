package com.senla.controller.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.dto.constants.Gender;
import com.senla.dto.constants.Status;
import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;
import com.senla.service.CustomUserService;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MvcResult;

class ProfileControllerTest extends AbstractMockControllerTest {

    @Autowired private CustomUserService customUserService;

    @Autowired private ObjectMapper objectMapper;

    private UUID getUserId() {
        String email = "user@gmail.com";
        return customUserService.findUserByEmail(email).getId();
    }

    @Test
    void givenExistingId_whenFindingById_thenReturnUserProfile() throws Exception {
        UUID id = getUserId();

        MvcResult mvcResult =
                mockMvc.perform(
                                get("/api/users/profile")
                                        .header("id", id)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(id, returnedUser.getId());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenTrowException() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(
                        get("/api/users/profile")
                                .header("id", id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenExistingId_whenUpdateById_thenReturnUpdatedUserProfile() throws Exception {
        UUID id = getUserId();
        UpdateUserDto updateUserDto =
                UpdateUserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .birthday(LocalDate.now())
                        .sex(Gender.MALE.name())
                        .phone("123456789")
                        .build();

        MvcResult mvcResult =
                mockMvc.perform(
                                put("/api/users/profile")
                                        .content(objectMapper.writeValueAsString(updateUserDto))
                                        .header("id", id)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(id, returnedUser.getId());
        Assertions.assertEquals(updateUserDto.getFirstName(), returnedUser.getFirstName());
    }

    @Test
    void givenNonExistingId_whenUpdateById_thenReturnStatusNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        UpdateUserDto updateUserDto =
                UpdateUserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .birthday(LocalDate.now())
                        .sex(Gender.MALE.name())
                        .phone("123456789")
                        .build();

        mockMvc.perform(
                        put("/api/users/profile")
                                .content(objectMapper.writeValueAsString(updateUserDto))
                                .header("id", id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenExistingId_whenChangePasswordById_thenReturnUpdatedUserProfile() throws Exception {
        UUID id = getUserId();
        ChangePasswordDto changePasswordDto =
                ChangePasswordDto.builder()
                        .password("newpassword")
                        .matchingPassword("newpassword")
                        .build();

        MvcResult mvcResult =
                mockMvc.perform(
                                patch("/api/users/profile")
                                        .content(objectMapper.writeValueAsString(changePasswordDto))
                                        .header("id", id)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(id, returnedUser.getId());
    }

    @Test
    void givenNonExistingId_whenChangePasswordById_thenReturnStatusNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        ChangePasswordDto changePasswordDto =
                ChangePasswordDto.builder()
                        .password("newpassword")
                        .matchingPassword("newpassword")
                        .build();

        mockMvc.perform(
                        patch("/api/users/profile")
                                .content(objectMapper.writeValueAsString(changePasswordDto))
                                .header("id", id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenExistingId_whenFindingById_thenReturnDeletedProfile() throws Exception {
        UUID id = getUserId();

        MvcResult mvcResult =
                mockMvc.perform(
                                delete("/api/users/profile")
                                        .header("id", id)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        DtoUser returnedUser =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), DtoUser.class);
        Assertions.assertEquals(id, returnedUser.getId());
        Assertions.assertEquals(Status.DELETED.name(), returnedUser.getStatus());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenReturnStatusNotFound() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(
                        delete("/api/users/profile")
                                .header("id", id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
