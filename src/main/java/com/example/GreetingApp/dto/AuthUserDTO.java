package com.example.GreetingApp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDTO {

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name must start with an uppercase letter")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name must start with an uppercase letter")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+]{8,}$",
            message = "Password must be at least 8 characters, contain 1 uppercase, 1 special character, and 1 number"
    )
    private String password;
}
