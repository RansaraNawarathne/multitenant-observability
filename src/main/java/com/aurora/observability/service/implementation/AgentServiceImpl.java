package com.aurora.observability.service.implementation;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.entity.Agent;
import com.aurora.observability.enums.AgentStatus;
import com.aurora.observability.enums.AgentType;
import com.aurora.observability.exception.ResourceNotFoundException;
import com.aurora.observability.mapper.AgentResponseDTOMapper;
import com.aurora.observability.mapper.CreateAgentRequestEntityMapper;
import com.aurora.observability.repository.AgentRepository;
import com.aurora.observability.service.AgentService;
import com.aurora.observability.utility.ObservabilityLogger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link AgentService} interface providing business logic
 * for creating and retrieving {@link Agent} entities.
 * <p>
 * This service handles conversion between DTOs and entities, applies domain-level
 * validation and defaulting rules, logs activity, and interacts with the {@link AgentRepository}
 * for persistence operations.
 */
@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final CreateAgentRequestEntityMapper createAgentRequestEntityMapper;
    private final AgentResponseDTOMapper agentResponseDTOMapper;

    /**
     * Creates a new {@link Agent} using the provided request data.
     * <p>
     * The method:
     * <ul>
     *     <li>Logs the create operation for observability.</li>
     *     <li>Maps the incoming {@link CreateAgentRequestDTO} to an {@link Agent} entity.</li>
     *     <li>Assigns a generated URL and sets {@link AgentStatus#ONLINE} if the agent is of type {@link AgentType#CLUSTER}.</li>
     *     <li>Saves the new agent to the repository.</li>
     * </ul>
     *
     * @param createAgentRequestDTO the request payload containing data required to create a new agent
     */
    @Transactional
    @Override
    public void createAgent(CreateAgentRequestDTO createAgentRequestDTO) {
        ObservabilityLogger.log(getClass().getName(), "Successfully agent created");
        Agent agentData = createAgentRequestEntityMapper.toEntity(createAgentRequestDTO);

        // Apply default settings for cluster-type agents
        if (agentData.getType().equals(AgentType.CLUSTER)) {
            agentData.setUrl("http://aurora.dev.agent-manager/" + UUID.randomUUID() + "/");
            agentData.setStatus(AgentStatus.ONLINE);
        }

        agentRepository.save(agentData);
    }

    /**
     * Retrieves all existing {@link Agent} records.
     * <p>
     * The method logs the retrieval process and converts entities into response DTOs.
     * If no agents are found, a {@link ResourceNotFoundException} is thrown.
     *
     * @return a list of {@link AgentResponseDTO} objects representing all agents
     * @throws ResourceNotFoundException if no agents exist in the system
     */
    @Override
    public List<AgentResponseDTO> getAllAgents() throws ResourceNotFoundException {
        ObservabilityLogger.log(getClass().getName(), "Retrieved all agents");
        List<Agent> agents = agentRepository.findAll();

        if (agents.isEmpty()) {
            throw new ResourceNotFoundException("Agents Not Found");
        }

        return agents.stream().map(agentResponseDTOMapper::toDto).toList();
    }
}
