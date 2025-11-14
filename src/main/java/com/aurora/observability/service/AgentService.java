package com.aurora.observability.service;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service interface defining operations related to managing agent resources.
 * <p>
 * This interface provides methods for creating new agents and retrieving
 * existing agents in a structured and domain-driven manner. Implementations
 * are expected to handle validation, transformation, persistence, and error
 * handling as appropriate.
 */
public interface AgentService {

    /**
     * Creates a new agent using the provided request data.
     * <p>
     * Implementations should handle:
     * <ul>
     *     <li>Validation of input DTO fields</li>
     *     <li>Mapping the DTO to an entity</li>
     *     <li>Applying domain-specific default values (if required)</li>
     *     <li>Persisting the new agent to the data store</li>
     * </ul>
     *
     * @param createAgentRequestDTO the payload containing required fields to create an agent
     */
    void createAgent(CreateAgentRequestDTO createAgentRequestDTO);

    /**
     * Retrieves all registered agents in the system.
     * <p>
     * Implementations should convert entity objects to {@link AgentResponseDTO}
     * before returning the results.
     *
     * @return a list of {@link AgentResponseDTO} objects representing all agents
     * @throws ResourceNotFoundException if no agent records are found
     */
    List<AgentResponseDTO> getAllAgents() throws ResourceNotFoundException;
}
