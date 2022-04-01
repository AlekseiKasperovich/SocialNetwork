package com.senla.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
public class HelloController {

    /**
     * @return
     */
    @GetMapping("/")
    public String home() {
        return "Hello World from Social Network";
    }
}
