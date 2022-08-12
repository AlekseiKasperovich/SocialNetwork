package com.senla.controller;

import com.senla.feign.FeignServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Aliaksei Kaspiarovich */
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final FeignServiceUtil feignServiceUtil;

    @GetMapping("/")
    public String home() {
        return "Hello World!!!";
    }

    @GetMapping("/go")
    public String go() {
        return feignServiceUtil.go();
    }
}
