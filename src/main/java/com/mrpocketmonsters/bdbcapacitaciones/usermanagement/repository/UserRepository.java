package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;

/**
 * Repository for managing user data.
 * It has no custom methods.
 * 
 * @author Nicolás Sabogal
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
}
