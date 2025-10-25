package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.RegisterRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling authentication-related endpoints such as login and registration.
 * 
 * @author Nicolás Sabogal
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    /** Authentication service for handling auth logic */
    private final AuthService authService;

    /** 
     * Endpoint for user login.
     * 
     * @param loginRequest The login request containing user credentials.
     * @return The response entity containing the login response or error details if login fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
        }
    }

    /** 
     * Endpoint for user registration.
     * 
     * @param registerRequest The registration request containing user details.
     * @return The response entity containing the registration response or error details if registration fails.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authService.register(registerRequest));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
        }
    }

}
