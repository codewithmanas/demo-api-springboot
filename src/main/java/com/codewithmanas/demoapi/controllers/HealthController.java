package com.codewithmanas.demoapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/")
    public String home() {
        return "App is running";
    }

}
