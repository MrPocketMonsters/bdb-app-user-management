package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service.AuthService;

/**
 * Configuration class for default data setup.
 * 
 * @author Nicolás Sabogal
 */
@Configuration
public class DefaultDataConfiguration {

    /** 
     * Bean for setting up default data on application startup.
     * 
     * @param authService The authentication service.
     * @return A CommandLineRunner that sets up default data.
     */
    @Bean
    public CommandLineRunner defaultDataSetup(AuthService authService) {
        return args -> authService.createAdminUserIfNotExists();
    }

}
