package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;

import lombok.Data;

/**
 * Data Transfer Object for user registration responses.
 * Contains the ID of the newly registered user.
 * 
 * @author Nicolás Sabogal
 */
@Data
public class UserIdentifierDto {
    
    /** ID of the newly registered user */
    private Long userId;

    /**
     * Factory method to create a UserIdentifierDto from a User entity.
     * 
     * @param user The User entity
     * @return A UserIdentifierDto containing the user's ID
     */
    public static UserIdentifierDto of(User user) {
        var response = new UserIdentifierDto();
        response.userId = user.getId();
        return response;
    }
    
}
