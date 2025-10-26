package com.mrpocketmonsters.bdbcapacitaciones.usermanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object for exception responses.
 * Includes fields for message and exception details.
 * 
 * @author Nicolás Sabogal
 */
@Data
@AllArgsConstructor
public class ExceptionResponse {

    /** The message describing the exception */
    private String message;
    /** The exception that occurred */
    private Exception exception;
    
}
