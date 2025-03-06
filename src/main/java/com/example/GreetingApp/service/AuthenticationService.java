package com.example.GreetingApp.service;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.model.AuthUser;
import com.example.GreetingApp.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.existsByEmail(authUserDTO.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }

        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword())); // Hash password

        authUserRepository.save(user);
        return "User registered successfully!";
    }
}
