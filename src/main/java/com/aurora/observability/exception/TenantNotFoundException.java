package com.aurora.observability.exception;

/**
 * Exception class that represents a scenario where a specified tenant
 * cannot be found or resolved within the system.
 *
 * <p>This exception extends {@link IllegalStateException}, indicating that
 * the system is in an unexpected or inconsistent state — for example,
 * when a tenant ID provided in a request does not correspond to a valid
 * tenant configuration in the current context.</p>
 *
 * <p>Typical use cases include:</p>
 * <ul>
 *   <li>When a tenant identifier (e.g., from a JWT token or request header) is invalid or missing.</li>
 *   <li>When the corresponding tenant configuration or database schema does not exist.</li>
 *   <li>When multi-tenant resolution fails during a service or repository call.</li>
 * </ul>
 *
 * <p>This exception is typically handled globally through a
 * {@link org.springframework.web.bind.annotation.ControllerAdvice} component
 * (e.g., {@code GlobalExceptionHandler}) to produce an appropriate
 * <strong>400 Bad Request</strong> or <strong>404 Not Found</strong> HTTP response.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * if (tenantConfig == null) {
 *     throw new TenantNotFoundException("Tenant with ID " + tenantId + " not found");
 * }
 * }</pre>
 */
public class TenantNotFoundException extends IllegalStateException {

    /**
     * Constructs a new {@code TenantNotFoundException} with the specified detail message.
     *
     * @param message the detail message that describes the cause of the exception.
     */
    public TenantNotFoundException (String message) {
        super(message);
    }
}
