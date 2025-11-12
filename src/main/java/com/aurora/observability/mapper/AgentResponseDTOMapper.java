package com.aurora.observability.mapper;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.entity.Agent;
import org.springframework.stereotype.Component;

/**
 * Mapper component that converts {@link Agent} entities to {@link AgentResponseDTO} objects.
 * <p>
 * This class is responsible for transforming database entities into data transfer objects (DTOs)
 * suitable for use in API responses. It helps maintain a clear separation between persistence
 * and presentation layers.
 * </p>
 */
@Component
public class AgentResponseDTOMapper implements DtoMapper<AgentResponseDTO, Agent>{

    /**
     * Converts an {@link Agent} entity into an {@link AgentResponseDTO}.
     * <p>
     * This method maps all relevant fields from the entity to the DTO,
     * ensuring that only necessary data is exposed to the client.
     * </p>
     *
     * @param value the {@link Agent} entity to be converted (must not be {@code null})
     * @return the mapped {@link AgentResponseDTO} instance
     */
    @Override
    public AgentResponseDTO toDto(Agent value) {
        return AgentResponseDTO.builder()
                .id(value.getId())
                .name(value.getName())
                .type(value.getType())
                .expireDate(value.getExpireDate())
                .url(value.getUrl())
                .status(value.getStatus())
                .build();
    }
}
