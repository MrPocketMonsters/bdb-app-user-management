package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Application-wide configuration class.
 * 
 * @author Nicolás Sabogal
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    /** User service for managing user-related operations */
    private final UserService userService;

    /**
     * UserDetailsService bean for loading user details by email
     *
     * @return The UserDetailsService instance
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userService.loadUserByEmail(email);
    }

    /**
     * PasswordEncoder bean for encoding passwords
     * 
     * @return The PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * AuthenticationManager bean for managing authentication
     * 
     * @param configuration The authentication configuration
     * @return The AuthenticationManager instance
     * @throws Exception if an error occurs while retrieving the AuthenticationManager
     */
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    /**
     * AuthenticationProvider bean for authenticating users
     * 
     * @return The AuthenticationProvider instance
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
}
