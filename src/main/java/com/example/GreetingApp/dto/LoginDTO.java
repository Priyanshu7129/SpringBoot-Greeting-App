package com.example.GreetingApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotNull(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Password is required")
    private String password;
}
