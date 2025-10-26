package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;

/**
 * Repository for managing user data.
 * 
 * @author Nicolás Sabogal
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email.
     * 
     * @param email The email of the user to find.
     * @return An Optional containing the found user, or empty if not found.
     */
    Optional<User> findByEmail(String email);
    
}
