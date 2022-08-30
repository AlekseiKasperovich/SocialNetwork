package com.senla.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.senla.dto.user.DtoCreateUser;
import com.senla.dto.user.DtoUser;
import com.senla.dto.user.ForgotPasswordDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

class AuthControllerTest extends AbstractControllerTest {

    @Test
    void givenUser_whenRegister_thenReturnUser() {
        DtoCreateUser dtoCreateUser =
                DtoCreateUser.builder()
                        .email("email@gmail.com")
                        .password("password")
                        .matchingPassword("password")
                        .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(dtoCreateUser, headers);

        ResponseEntity<DtoUser> response =
                testRestTemplate.exchange(
                        "/api/auth/registration", HttpMethod.POST, request, DtoUser.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(dtoCreateUser.getEmail(), response.getBody().getEmail());
    }

    @Test
    void givenExistingEmail_whenRegister_thenStatusConflict() {
        DtoCreateUser dtoCreateUser =
                DtoCreateUser.builder()
                        .email("user@gmail.com")
                        .password("password")
                        .matchingPassword("password")
                        .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(dtoCreateUser, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/auth/registration", HttpMethod.POST, request, Object.class);

        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void givenExistingEmail_whenSendPassword_thenSaveNewPassword() {
        String email = "user@gmail.com";
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto(email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(forgotPasswordDto, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/auth/password/new", HttpMethod.POST, request, Object.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void givenNonExistingEmail_whenSendPassword_thenStatusNotFound() {
        String email = "another@gmail.com";
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto(email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(forgotPasswordDto, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/auth/password/new", HttpMethod.POST, request, Object.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
