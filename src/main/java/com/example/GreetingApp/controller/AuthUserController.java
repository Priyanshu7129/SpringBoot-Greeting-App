package com.example.GreetingApp.controller;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.AuthResponseDTO;
import com.example.GreetingApp.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthenticationService authenticationService;

    public AuthUserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthUserDTO userDTO) {
        authenticationService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully"); // ✅ Return only message
    }



    // Authenticate user and generate JWT token
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        AuthResponseDTO response = authenticationService.authenticate(authUserDTO);
        return ResponseEntity.ok(response);
    }
}
