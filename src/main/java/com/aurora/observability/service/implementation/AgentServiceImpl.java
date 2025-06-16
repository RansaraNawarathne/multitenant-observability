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

@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final CreateAgentRequestEntityMapper createAgentRequestEntityMapper;
    private final AgentResponseDTOMapper agentResponseDTOMapper;

    @Transactional
    @Override
    public void createAgent(CreateAgentRequestDTO createAgentRequestDTO) {
        ObservabilityLogger.log(getClass().getName(), "Successfully agent created");
        Agent agentData = createAgentRequestEntityMapper.toEntity(createAgentRequestDTO);

        if (agentData.getType().equals(AgentType.CLUSTER)) {
            agentData.setUrl("http://aurora.dev.agent-manager/"+ UUID.randomUUID()+"/");
            agentData.setStatus(AgentStatus.ONLINE);
        }

        agentRepository.save(agentData);
    }

    @Override
    public List<AgentResponseDTO> getAllAgents() throws ResourceNotFoundException{
        ObservabilityLogger.log(getClass().getName(), "Retrieved all agents");
        List<Agent> agents = agentRepository.findAll();

        if (agents.isEmpty()) {
            throw new ResourceNotFoundException("Agents Not Found");
        }

        return agents.stream().map(agentResponseDTOMapper::toDto).toList();
    }
}
