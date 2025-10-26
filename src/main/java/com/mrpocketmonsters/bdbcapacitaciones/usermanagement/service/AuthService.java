package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.LoginResponse;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.RegisterRequest;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.Role;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;
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

    /** User repository for accessing user data */
    private final UserRepository userRepository;

    /** Password encoder for hashing passwords */
    private final PasswordEncoder passwordEncoder;

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
    public void register(RegisterRequest request) {
        validateEmailFormat(request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("The email " + request.getEmail() + " is already in use");
        }
        
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .createdAt(Instant.now())
                .roles(roles)
                .build();

        userRepository.save(user);
    }

    /**
     * Validates the format of the provided email.
     * 
     * @param email The email to validate.
     * @throws IllegalArgumentException if the email format is invalid.
     */
    private void validateEmailFormat(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("Error: the email " + email + " is invalid");
        }
    }

}
