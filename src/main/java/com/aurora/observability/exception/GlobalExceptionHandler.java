package com.aurora.observability.exception;

import com.aurora.observability.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for the application.
 *
 * <p>
 * This class provides centralized exception handling across all controllers
 * using the {@link ControllerAdvice} annotation. It intercepts exceptions
 * thrown by controller methods and converts them into standardized
 * {@link ResponseEntity} responses with appropriate HTTP status codes
 * and structured error messages.
 * </p>
 *
 * <p>
 * Each exception handler builds a {@link ResponseDTO} object containing:
 * <ul>
 *   <li><b>timestamp</b> – the time the error occurred</li>
 *   <li><b>statusCode</b> – the HTTP status code</li>
 *   <li><b>error</b> – a short error type or title</li>
 *   <li><b>message</b> – a detailed error message</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example response:
 * <pre>
 * {
 *   "timestamp": "2025-10-07T15:32:10.123",
 *   "statusCode": 404,
 *   "error": "Not Found",
 *   "message": "Requested resource not found"
 * }
 * </pre>
 * </p>
 *
 * @author Malindu
 * @since 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where a requested resource cannot be found.
     *
     * <p>
     * This method is triggered when a {@link ResourceNotFoundException} is thrown.
     * It constructs a {@link ResponseDTO} containing details of the error and returns
     * it with an HTTP 404 (Not Found) status.
     * </p>
     *
     * @param ex the {@link ResourceNotFoundException} thrown by a controller or service
     * @return a {@link ResponseEntity} with a {@link ResponseDTO} body and HTTP 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound (ResourceNotFoundException ex) {
        ResponseDTO body = ResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles cases where a specified tenant cannot be found or does not exist.
     *
     * <p>
     * This method is triggered when a {@link TenantNotFoundException} is thrown.
     * It constructs a {@link ResponseDTO} containing relevant error details
     * and returns it with an HTTP 400 (Bad Request) status.
     * </p>
     *
     * @param ex the {@link TenantNotFoundException} thrown by a controller or service
     * @return a {@link ResponseEntity} with a {@link ResponseDTO} body and HTTP 400 status
     */
    @ExceptionHandler(TenantNotFoundException.class)
    public ResponseEntity<Object> handleTenantNotFound (TenantNotFoundException ex) {
        ResponseDTO body = ResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error("Tenant Not Found")
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
