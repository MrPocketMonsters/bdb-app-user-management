package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object for user login responses.
 * Includes fields for JWT token, token type, and expiration time.
 * 
 * @author Nicolás Sabogal
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    /** The JWT token */
    public String token;
    /** The type of the token */
    public String type;
    /** The expiration time of the token */
    public Long expiresIn;

}
