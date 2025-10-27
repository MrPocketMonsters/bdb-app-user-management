package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto.ExceptionResponse;

/**
 * Global exception handler for the API.
 * Centralizes HTTP responses for common exceptions.
 * 
 * @author Nicolás Sabogal
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle entity not found exceptions.
     * Returns a 404 Not Found status with details of the exception.
     * 
     * @param ex the entity not found exception
     * @return a ResponseEntity with status 404 and details of the exception
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException ex) {
        log.debug("Entity not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(ex.getMessage(), ex));
    }

    /**
     * Handle constraint violation exceptions.
     * Returns a 400 Bad Request status with details of the exception.
     * 
     * @param ex the constraint violation exception
     * @return a ResponseEntity with status 400 and details of the exception
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining("; "));
        log.debug("Constraint violation: {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(message, ex));
    }

    /**
     * Handle method argument not valid exceptions.
     * Returns a 400 Bad Request status with details of the exception.
     * 
     * @param ex the method argument not valid exception
     * @return a ResponseEntity with status 400 and details of the exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.debug("Validation failed: {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(message, ex));
    }

    /**
     * Handle HTTP message not readable exceptions.
     * Returns a 400 Bad Request status with details of the exception.
     * 
     * @param ex the HTTP message not readable exception
     * @return a ResponseEntity with status 400 and details of the exception
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.debug("Malformed request body: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("Malformed request body: " + ex.getMessage(), ex));
    }

    /**
     * Handle access denied exceptions.
     * Returns a 403 Forbidden status with details of the exception.
     * 
     * @param ex the access denied exception
     * @return a ResponseEntity with status 403 and details of the exception
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDenied(AccessDeniedException ex) {
        log.debug("Access denied: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionResponse(ex.getMessage(), ex));
    }

    /**
     * Handle authentication exceptions.
     * Returns a 401 Unauthorized status with details of the exception.
     * 
     * @param ex the authentication exception
     * @return a ResponseEntity with status 401 and details of the exception
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthentication(AuthenticationException ex) {
        log.debug("Authentication failed: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(ex.getMessage(), ex));
    }

    /**
     * Handle generic exceptions.
     * Returns a 500 Internal Server Error status with details of the exception.
     * 
     * @param ex the generic exception
     * @return a ResponseEntity with status 500 and details of the exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse("Internal server error: " + ex.getMessage(), ex));
    }

}
