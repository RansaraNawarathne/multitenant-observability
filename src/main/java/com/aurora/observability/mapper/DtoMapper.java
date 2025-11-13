package com.aurora.observability.mapper;

/**
 * Generic interface for mapping entity objects to Data Transfer Objects (DTOs).
 * <p>
 * Implementations of this interface define how to transform an entity of type {@code R}
 * into a corresponding DTO of type {@code T}. This promotes a clean separation between
 * persistence and presentation layers, ensuring consistent and reusable mapping logic
 * across the application.
 * </p>
 *
 * @param <T> the type of the DTO (target type)
 * @param <R> the type of the entity or source object
 */
public interface DtoMapper<T, R> {

    /**
     * Converts an entity or source object of type {@code R} into a DTO of type {@code T}.
     *
     * @param value the source entity or object to convert (must not be {@code null})
     * @return the corresponding DTO representation of the source object
     */
    T toDto(R value);
}
