package com.aurora.observability.mapper;

import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.entity.Agent;
import org.springframework.stereotype.Component;

@Component
public class CreateAgentRequestEntityMapper implements EntityMapper<Agent, CreateAgentRequestDTO> {

    @Override
    public Agent toEntity(CreateAgentRequestDTO value) {
        return Agent.builder()
                .name(value.getName())
                .type(value.getType())
                .expireDate(value.getExpireDate())
                .build();
    }
}
