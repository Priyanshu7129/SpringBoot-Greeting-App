package com.example.GreetingApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {
    private String username;
    private String password;
    private String role; // ✅ Add role field

    // Constructor
    public AuthUserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {  // ✅ Add getter for role
        return role;
    }
}

