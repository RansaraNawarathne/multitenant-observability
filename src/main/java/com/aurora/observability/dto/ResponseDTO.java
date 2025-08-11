package com.aurora.observability.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * A generic Data Transfer Object (DTO) for sending API responses in a
 * standardized format.
 * <p>
 * This class is typically used to wrap responses returned by controllers or
 * service methods, ensuring consistency in the structure of the JSON response.
 * </p>
 *
 * <p>Features:</p>
 * <ul>
 *   <li>Includes an optional payload ({@link #data}) for returning results.</li>
 *   <li>Captures a timestamp for when the response was generated.</li>
 *   <li>Includes HTTP status and status code for easy client interpretation.</li>
 *   <li>Provides optional fields for error details and human-readable messages.</li>
 *   <li>Ignores {@code null} fields in JSON output to reduce payload size.</li>
 * </ul>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * ResponseDTO response = ResponseDTO.builder()
 *     .data(user)
 *     .timestamp(LocalDateTime.now())
 *     .status(HttpStatus.OK)
 *     .statusCode(HttpStatus.OK.value())
 *     .message("User fetched successfully")
 *     .build();
 * }</pre>
 *
 */
@Data
@AllArgsConstructor
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    /**
     * The payload of the response.
     * <p>
     * Can be any type of object (e.g., DTO, collection).
     * This is typically {@code null} when an error occurs.
     * </p>
     */
    private Object data;

    /**
     * The date and time when the response was generated.
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status of the response (e.g., {@link HttpStatus#OK}, {@link HttpStatus#BAD_REQUEST}).
     */
    private HttpStatus status;

    /**
     * The numeric HTTP status code (e.g., 200, 400, 404).
     * <p>Stored separately for convenience and easy JSON serialization.</p>
     */
    private int statusCode;

    /**
     * A brief error description, typically set when {@link #status} indicates failure.
     * <p>Example: {@code "Validation Error"}.</p>
     */
    private String error;

    /**
     * A human-readable message describing the result of the operation.
     * <p>Example: {@code "User fetched successfully"} or {@code "Invalid request parameters"}.</p>
     */
    private String message;
}
