package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginResponse;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.PasswordChangeRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.RegisterRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service.AuthService;

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
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    /** 
     * Endpoint for user registration.
     * 
     * @param registerRequest The registration request containing user details.
     * @return The response entity containing the registration response or error details if registration fails.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Method to change a user's password.
     * 
     * @param id The ID of the user whose password is to be changed.
     * @param passwordChangeRequest The PasswordChangeRequest containing current and new passwords.
     * @return UserDto containing the user details after password change.
     */
    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        authService.changePassword(
            passwordChangeRequest.getEmail(),
            passwordChangeRequest.getCurrentPassword(),
            passwordChangeRequest.getNewPassword()
        );

        return ResponseEntity.ok().build();
    }

}
