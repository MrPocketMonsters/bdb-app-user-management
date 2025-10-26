package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    /** Authentication manager for handling authentication */
    private final AuthenticationManager authenticationManager;

    /** JWT Service for handling JWT operations */
    private final JwtService jwtService;

    /** Environment for accessing application properties */
    private final Environment environment;

    /**
     * Handles user login.
     * 
     * @param loginRequest The login request containing user credentials.
     * @return The response object containing JWT token and expiration time.
     */
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            ));

        String token = jwtService.getToken((UserDetails) authentication.getPrincipal());
        Long expirationTime = environment.getProperty("jwt.expiration.time", Long.class);

        return new LoginResponse(token, "Bearer", expirationTime);
    }

    /**
     * Handles user registration.
     * 
     * @param registerRequest The registration request containing user details.
     * @return The response object containing JWT token and expiration time.
     */
    public void register(RegisterRequest registerRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
    
}
