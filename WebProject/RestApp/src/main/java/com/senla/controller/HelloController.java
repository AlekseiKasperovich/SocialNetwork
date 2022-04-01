package com.senla.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "Hello World!!!";
    }

    @GetMapping("/go")
    public String go() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange("${request.host}" + "/",
                HttpMethod.GET, new HttpEntity<>(headers),
                String.class).getBody();
    }

}
