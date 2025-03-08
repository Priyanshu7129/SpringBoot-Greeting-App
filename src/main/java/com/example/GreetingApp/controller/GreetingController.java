package com.example.GreetingApp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting") // ✅ Ensures correct mapping
public class GreetingController {

    @GetMapping
    public String getGreeting() {
        return "Hello, Priyanshu";
    }
}
