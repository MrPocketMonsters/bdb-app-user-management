package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;

import lombok.Data;

/**
 * Data Transfer Object for user list elements.
 * Contains user details for listing purposes.
 * 
 * @author Nicolás Sabogal
 */
@Data
public class UserListElement {

    /** The ID of the user */
    private Long id;
    /** The email of the user */
    private String email;
    /** The name of the user */
    private String name;
    /** The creation date of the user */
    private String createdAt;
    /** The roles assigned to the user */
    private Set<String> roles;

    /** 
     * Converts a User entity to a UserListElement DTO.
     * 
     * @param user The User entity to convert.
     * @return The corresponding UserListElement DTO.
     */
    public static UserListElement of(User user) {
        UserListElement dto = new UserListElement();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setCreatedAt(user.getCreatedAt().toString());
        dto.setRoles(user.getRoles().stream()
            .map(role -> role.name())
            .collect(Collectors.toSet()));

        return dto;
    }
    
}
