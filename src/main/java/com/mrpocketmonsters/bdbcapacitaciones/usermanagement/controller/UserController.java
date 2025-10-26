package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.ExceptionResponse;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.UserListElement;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.entity.User;
import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(
                users.stream()
                    .map(user -> UserListElement.of(user))
                    .toList()
            );
        } catch (Exception exception) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), exception);
            return ResponseEntity.internalServerError().body(exceptionResponse);
        }
    }
    
}
