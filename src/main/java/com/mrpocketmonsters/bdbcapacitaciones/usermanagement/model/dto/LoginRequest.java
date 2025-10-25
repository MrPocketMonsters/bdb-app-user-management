package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import lombok.Data;

/**
 * Data Transfer Object for user login requests.
 * Includes fields for username and password.
 * 
 * @author Nicolás Sabogal
 */
@Data
public class LoginRequest {

    /** User's username */
    private String username;
    /** User's password */
    private String password;
    
}
