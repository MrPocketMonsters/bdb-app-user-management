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

    /** User's email */
    private String email;
    /** User's password */
    private String password;
    
}
