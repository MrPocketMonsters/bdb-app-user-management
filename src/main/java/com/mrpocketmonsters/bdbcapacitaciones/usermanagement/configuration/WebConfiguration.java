package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;

/**
 * Web configuration class.
 * 
 * @author Nicolás Sabogal
 */
@Configuration
@RequiredArgsConstructor
public class WebConfiguration {

    /** Environment for accessing application properties */
    private final Environment environment;

    /**
     * CorsConfigurationSource bean for configuring CORS settings.
     * 
     * @return The CorsConfigurationSource instance
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            var configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of(environment.getProperty("cors.url")));
            configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setAllowCredentials(true);

            return configuration;
        };
    }
    
}
