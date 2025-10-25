package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service;

import org.springframework.stereotype.Service;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginResponse;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.RegisterRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.RegisterResponse;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service for handling authentication logic such as login and registration.
 * 
 * @author Nicolás Sabogal
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /** User repository for accessing user data */
    private final UserRepository userRepository;

    /**
     * Handles user login.
     * 
     * @param loginRequest The login request containing user credentials.
     * @return The response object containing JWT token and expiration time.
     */
    public LoginResponse login(LoginRequest loginRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    /**
     * Handles user registration.
     * 
     * @param registerRequest The registration request containing user details.
     * @return The response object containing JWT token and expiration time.
     */
    public RegisterResponse register(RegisterRequest registerRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
    
}
