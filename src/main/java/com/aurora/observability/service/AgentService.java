package com.aurora.observability.service;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.exception.ResourceNotFoundException;

import java.util.List;

public interface AgentService {
    public void createAgent (CreateAgentRequestDTO createAgentRequestDTO);
    public List<AgentResponseDTO> getAllAgents() throws ResourceNotFoundException;
}
