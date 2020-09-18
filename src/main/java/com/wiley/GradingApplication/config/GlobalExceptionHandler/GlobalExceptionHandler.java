package com.wiley.GradingApplication.config.GlobalExceptionHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Global Exception Handler
     *
     * @param ex      The target exception
     * @param request The current request
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false), ("global.exception"));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Validation Exception
     *
     * @param ex      The target exception
     * @param request The current request
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> validationException(ValidationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * DataIntegrity Violation Exception
     *
     * @param ex      The target exception
     * @param request The current request
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false), ("api.message.fk.constrain"));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * SQL Integrity Constraint Violation Exception
     *
     * @param ex      The target exception
     * @param request The current request
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> sqlIntegrityViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false), ("api.message.sql.constraint.violation"));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Resource Not Found Exception
     *
     * @param ex      The target exception
     * @param request The current request
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }



}
