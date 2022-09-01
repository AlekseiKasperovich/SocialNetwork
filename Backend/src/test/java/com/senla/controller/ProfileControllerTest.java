package com.senla.controller;

import com.senla.dto.constants.Gender;
import com.senla.dto.constants.Status;
import com.senla.dto.profile.ChangePasswordDto;
import com.senla.dto.profile.UpdateUserDto;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.UserDetailsDto;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

class ProfileControllerTest extends AbstractControllerTest {

    private UUID getUserId() {
        String email = "user@gmail.com";
        HttpHeaders headers = new HttpHeaders();
        headers.set("email", email);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);
        ResponseEntity<UserDetailsDto> response =
                testRestTemplate.exchange(
                        "/api/users/details", HttpMethod.GET, request, UserDetailsDto.class);
        return response.getBody().getId();
    }

    @Test
    void givenExistingId_whenFindingById_thenReturnUserProfile() {
        UUID id = getUserId();
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", id.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);

        ResponseEntity<DtoUser> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.GET, request, DtoUser.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(id, response.getBody().getId());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenStatusNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", UUID.randomUUID().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.GET, request, Object.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void givenExistingId_whenUpdateById_thenReturnUpdatedUserProfile() {
        UUID id = getUserId();
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", id.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        UpdateUserDto updateUserDto =
                UpdateUserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .birthday(LocalDate.now())
                        .sex(Gender.MALE.name())
                        .phone("123456789")
                        .build();
        HttpEntity request = new HttpEntity<>(updateUserDto, headers);

        ResponseEntity<DtoUser> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.PUT, request, DtoUser.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(updateUserDto.getFirstName(), response.getBody().getFirstName());
    }

    @Test
    void givenNonExistingId_whenUpdateById_thenReturnStatusNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", UUID.randomUUID().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        UpdateUserDto updateUserDto =
                UpdateUserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .birthday(LocalDate.now())
                        .sex(Gender.MALE.name())
                        .phone("123456789")
                        .build();
        HttpEntity request = new HttpEntity<>(updateUserDto, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.PUT, request, Object.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void givenExistingId_whenChangePasswordById_thenReturnUpdatedUserProfile() {
        UUID id = getUserId();
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", id.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        ChangePasswordDto changePasswordDto =
                ChangePasswordDto.builder()
                        .password("newpassword")
                        .matchingPassword("newpassword")
                        .build();
        HttpEntity request = new HttpEntity<>(changePasswordDto, headers);

        ResponseEntity<DtoUser> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.PATCH, request, DtoUser.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void givenNonExistingId_whenChangePasswordById_thenReturnStatusNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", UUID.randomUUID().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        ChangePasswordDto changePasswordDto =
                ChangePasswordDto.builder()
                        .password("newpassword")
                        .matchingPassword("newpassword")
                        .build();
        HttpEntity request = new HttpEntity<>(changePasswordDto, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.PATCH, request, Object.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void givenExistingId_whenFindingById_thenReturnDeletedProfile() {
        UUID id = getUserId();
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", id.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);

        ResponseEntity<DtoUser> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.DELETE, request, DtoUser.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Status.DELETED.name(), response.getBody().getStatus());
    }

    @Test
    void givenNonExistingId_whenFindingById_thenReturnStatusNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", UUID.randomUUID().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/users/profile", HttpMethod.DELETE, request, Object.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
