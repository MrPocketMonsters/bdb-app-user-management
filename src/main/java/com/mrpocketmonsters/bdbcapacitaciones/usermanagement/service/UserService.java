package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.UserDto;
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
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found"));
    }

    /**
     * Retrieves a list of all users.
     * 
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();   
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param id The ID of the user to retrieve.
     * @return The User with the specified ID.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Updates a user's details.
     * 
     * @param userDto The UserDto containing updated user details.
     * @return The updated User.
     */
    public User updateUser(UserDto userDto) {
        User user = getUserById(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }

    /**
     * Disables a user by their ID.
     * 
     * @param id The ID of the user to disable.
     */
    public User disableUser(Long id) {
        User user = getUserById(id);
        user.setEnabled(false);
        return userRepository.save(user);
    }

}
