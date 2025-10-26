package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service for managing user-related operations.
 * 
 * @author Nicolás Sabogal
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /** User repository for accessing user data */
    private final UserRepository userRepository;

    /**
     * Loads a user by their email.
     * 
     * @param email The email of the user to load.
     * @return The UserDetails of the found user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    public UserDetails loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found"));
    }

    /**
     * Retrieves a list of all users with passwords set to null.
     * 
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(user -> {
                user.setPassword(null);
                return user;
            })
            .toList();
    }


}
