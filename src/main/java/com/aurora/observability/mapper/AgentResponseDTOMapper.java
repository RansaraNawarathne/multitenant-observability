package com.aurora.observability.mapper;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.entity.Agent;
import org.springframework.stereotype.Component;

@Component
public class AgentResponseDTOMapper implements DtoMapper<AgentResponseDTO, Agent>{

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
