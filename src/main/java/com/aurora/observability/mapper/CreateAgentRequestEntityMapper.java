package com.aurora.observability.mapper;

import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.entity.Agent;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting {@link CreateAgentRequestDTO} objects
 * into {@link Agent} entities.
 * <p>
 * This class helps isolate the mapping logic between the API request layer
 * and the persistence layer, ensuring a clean separation of concerns.
 * </p>
 */
@Component
public class CreateAgentRequestEntityMapper implements EntityMapper<Agent, CreateAgentRequestDTO> {

    /**
     * Converts a {@link CreateAgentRequestDTO} into an {@link Agent} entity.
     * <p>
     * This method maps relevant fields from the DTO to the entity, preparing it
     * for persistence. Only the properties required at creation time are populated.
     * </p>
     *
     * @param value the {@link CreateAgentRequestDTO} containing agent creation details (must not be {@code null})
     * @return a new {@link Agent} entity populated with values from the DTO
     */
    @Override
    public Agent toEntity(CreateAgentRequestDTO value) {
        return Agent.builder()
                .name(value.getName())
                .type(value.getType())
                .expireDate(value.getExpireDate())
                .build();
    }
}
