package com.example.GreetingApp.controller;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthenticationService authenticationService;

    public AuthUserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid AuthUserDTO authUserDTO) {
        return authenticationService.registerUser(authUserDTO);
    }
}
