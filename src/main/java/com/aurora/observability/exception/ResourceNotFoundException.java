package com.aurora.observability.exception;

/**
 * Exception class that represents a scenario where a requested resource
 * cannot be found within the system.
 *
 * <p>This exception extends {@link RuntimeException}, making it an
 * unchecked exception that can be thrown during runtime without
 * requiring explicit handling.</p>
 *
 * <p>Typical use cases include:</p>
 * <ul>
 *   <li>When an entity (e.g., agent, or record) does not exist in the database.</li>
 *   <li>When a requested file or configuration is missing.</li>
 *   <li>When an API endpoint receives an invalid resource identifier.</li>
 * </ul>
 *
 * <p>This exception is commonly handled globally using
 * {@link org.springframework.web.bind.annotation.ControllerAdvice}
 * (e.g., in {@code GlobalExceptionHandler}) to return an appropriate
 * HTTP response such as <strong>404 Not Found</strong>.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * if (resource == null) {
 *     throw new ResourceNotFoundException("Resource with ID " + id + " not found");
 * }
 * }</pre>
 */
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Constructs a new {@code ResourceNotFoundException} with the specified detail message.
     *
     * @param message the detail message that describes the cause of the exception.
     */
    public ResourceNotFoundException (String message) {
        super (message);
    }
}
