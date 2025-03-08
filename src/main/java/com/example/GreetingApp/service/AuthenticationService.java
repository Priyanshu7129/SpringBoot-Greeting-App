package com.example.GreetingApp.service;

import com.example.GreetingApp.dto.AuthResponseDTO;
import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.model.AuthUser;
import com.example.GreetingApp.repository.AuthUserRepository;
import com.example.GreetingApp.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user
    public AuthResponseDTO registerUser(AuthUserDTO userDTO) {
        AuthUser user = new AuthUser();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // ✅ Assign a default role if none is provided
        if (userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
            user.setRole("USER"); // Set default role
        } else {
            user.setRole(userDTO.getRole()); // Use provided role
        }

        authUserRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token, "User registered successfully");
    }


    // Authenticate user & generate JWT token
    public AuthResponseDTO authenticate(AuthUserDTO authUserDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUserDTO.getUsername(), authUserDTO.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthResponseDTO(token, "Login successful");
    }
}
