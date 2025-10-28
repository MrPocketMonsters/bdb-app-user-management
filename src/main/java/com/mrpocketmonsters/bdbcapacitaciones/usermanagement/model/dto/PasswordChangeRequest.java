package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import lombok.Data;

/**
 * Data Transfer Object for password change requests.
 * Contains the current and new passwords.
 * 
 * @author Nicolás Sabogal
 */
@Data
public class PasswordChangeRequest {

    /** The email of the user requesting the password change */
    private String email;
    /** The current password of the user */
    private String currentPassword;
    /** The new password to set for the user */
    private String newPassword;
    
}
