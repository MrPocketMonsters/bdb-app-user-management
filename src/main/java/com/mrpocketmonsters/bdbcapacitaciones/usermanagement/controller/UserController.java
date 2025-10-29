package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.UserDto;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.UserListElement;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * Controller for managing user-related endpoints.
 * 
 * @author Nicolás Sabogal
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    /** User service for managing user-related operations */
    private final UserService userService;

    /**
     * Method to get all users without exposing passwords.
     * 
     * @return ResponseEntity containing a list of all users.
     */
    @GetMapping("/")
    public ResponseEntity<List<UserListElement>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(
            users.stream()
                .map(user -> UserListElement.of(user))
                .toList()
        );
    }

    /**
     * Method to get a user by their ID.
     * 
     * @param id The ID of the user to retrieve.
     * @return UserDto containing user details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserDto.of(user));
    }
    
    /**
     * Method to modify a user's details.
     * 
     * @param id The ID of the user to modify.
     * @param userDto The UserDto containing updated user details.
     * @return UserDto containing the modified user details.
     */
    @PostMapping("/{id}")
    public ResponseEntity<UserDto> modifyUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (userDto.getId() != null && !id.equals(userDto.getId()))
            throw new IllegalArgumentException("ID in path and body do not match");
            
        User user = userService.updateUser(userDto);
        return ResponseEntity.ok(UserDto.of(user));
    }
    
    /**
     * Method to disable a user by their ID.
     * 
     * @param id The ID of the user to disable.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableUser(@PathVariable Long id) {
        User user = userService.disableUser(id);
        if (user.isEnabled())
            throw new IllegalStateException("User could not be disabled");
    
        return ResponseEntity.ok().build();
    }

    /**
     * Method to get a user by their email.
     * 
     * @param email The email of the user to retrieve.
     * @return UserDto containing user details.
     */
    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        User user = userService.loadUserByEmail(email);
        return ResponseEntity.ok(UserDto.of(user));
    }

}
