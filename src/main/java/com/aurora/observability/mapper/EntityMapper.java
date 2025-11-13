package com.aurora.observability.mapper;

/**
 * Generic interface for mapping Data Transfer Objects (DTOs) to entity objects.
 * <p>
 * Implementations of this interface define the transformation logic required to
 * convert an input object of type {@code R} (typically a DTO) into a corresponding
 * entity object of type {@code T}. This promotes reusability and a clear separation
 * of concerns between the API layer and the persistence layer.
 * </p>
 *
 * @param <T> the type of the entity (target type)
 * @param <R> the type of the source object, typically a DTO
 */
public interface EntityMapper<T, R> {

    /**
     * Converts a source object of type {@code R} into an entity of type {@code T}.
     *
     * @param value the source object to be converted (must not be {@code null})
     * @return the corresponding entity object populated with mapped values
     */
    T toEntity(R value);
}
