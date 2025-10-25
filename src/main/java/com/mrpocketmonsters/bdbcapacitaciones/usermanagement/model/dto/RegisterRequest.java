package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import lombok.Data;

/**
 * Data Transfer Object for user registration requests.
 * Includes fields for email, password, and name.
 * 
 * @author Nicolás Sabogal
 */
@Data
public class RegisterRequest {

    /** User's email address */
    private String email;
    /** User's password */
    private String password;
    /** User's name */
    private String name;

}
