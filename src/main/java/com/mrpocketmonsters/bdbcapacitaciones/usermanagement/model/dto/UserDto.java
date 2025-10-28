package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;

import lombok.Data;

/**
 * Data Transfer Object for user details.
 * Includes fields for id, email, and name.
 * 
 * @author Nicolás Sabogal
 */
@Data
public class UserDto {

    /** User's unique identifier */
    private Long id;
    /** User's email */
    private String email;
    /** User's full name */
    private String name;

    /** 
     * Converts a User entity to a UserDto.
     * 
     * @param user The User entity to convert.
     * @return The corresponding UserDto.
     */
    public static UserDto of(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());

        return dto;
    }

}
