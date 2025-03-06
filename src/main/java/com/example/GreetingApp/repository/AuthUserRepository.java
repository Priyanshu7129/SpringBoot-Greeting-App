package com.example.GreetingApp.repository;

import com.example.GreetingApp.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    boolean existsByEmail(String email);
}
