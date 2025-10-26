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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a user in the system.
 * Maps to the "appuser" table in the database.
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
    name = "appuser",
    uniqueConstraints = @UniqueConstraint(columnNames = "email_appuser")
)
public class User implements UserDetails {
    
    /** Unique identifier for the user */
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seq_appuser"
    )
    @SequenceGenerator(
        name = "seq_appuser",
        sequenceName = "seq_appuser",
        allocationSize = 1
    )
    @NotNull
    @Column(
        name = "id_appuser",
        nullable = false
    )
    private Long id;

    /** Email address of the user */
    @Email
    @NotEmpty
    @Column(
        name = "email_appuser",
        length = 64,
        nullable = false
    )
    private String email;

    /** Password of the user */
    @NotEmpty
    @Column(
        name = "password_appuser",
        length = 128,
        nullable = false
    )
    private String password;

    /** Name of the user */
    @NotEmpty
    @Column(
        name = "name_appuser",
        length = 64,
        nullable = false
    )
    private String name;
    
    /** Timestamp when the user was created */
    @Column(
        name = "created_at_appuser",
        nullable = false,
        updatable = false
    )
    private Instant createdAt;

    /** Roles assigned to the user */
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(
        name = "role",
        joinColumns = @JoinColumn(name = "id_appuser")
    )
    @Column(name = "roles_appuser")
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
