package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a user in the system.
 * Maps to the "user" table in the database.
 * 
 * @author Nicolás Sabogal
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "user",
    uniqueConstraints = @UniqueConstraint(columnNames = "email_user")
)
public class User implements UserDetails {
    
    /** Unique identifier for the user */
    @Id
    @NotEmpty
    @Column(
        name = "id_user",
        nullable = false
    )
    private Long id;

    /** Email address of the user */
    @Email
    @NotEmpty
    @Column(
        name = "email_user",
        length = 64,
        nullable = false
    )
    private String email;

    /** Password of the user */
    @NotEmpty
    @Column(
        name = "password_user",
        length = 128,
        nullable = false
    )
    private String password;

    /** Name of the user */
    @NotEmpty
    @Column(
        name = "name_user",
        length = 64,
        nullable = false
    )
    private String name;
    
    /** Timestamp when the user was created */
    @Column(
        name = "created_at_user",
        nullable = false,
        updatable = false
    )
    private Instant createdAt;

    /** Roles assigned to the user */
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(
        name = "role",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "user_roles")
    private Set<Role> roles;

    /** Get the authorities granted to the user */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.name()))
            .toList();
    }

    /** Get the username of the user */
    @Override
    public String getUsername() {
        return this.email;
    }
    
}
