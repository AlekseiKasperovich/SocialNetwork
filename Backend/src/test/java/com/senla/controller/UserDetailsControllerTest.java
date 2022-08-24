package com.senla.controller;

import com.senla.dto.user.UserDetailsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.MediaType;

class UserDetailsControllerTest extends AbstractControllerTest {

    @Test
    void givenExistingEmail_whenFindingByEmail_thenReturnUser() {
        String email = "user@gmail.com";
        HttpHeaders headers = new HttpHeaders();
        headers.set("email", email);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);

        ResponseEntity<UserDetailsDto> response =
                testRestTemplate.exchange(
                        "/api/users/details", HttpMethod.GET, request, UserDetailsDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(email, response.getBody().getEmail());
    }

    @Test
    void givenNonExistingEmail_whenFindingByEmail_thenStatusNotFound() {
        String email = "another@gmail.com";
        HttpHeaders headers = new HttpHeaders();
        headers.set("email", email);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response =
                testRestTemplate.exchange(
                        "/api/users/details", HttpMethod.GET, request, Object.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
