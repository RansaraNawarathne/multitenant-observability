package com.aurora.observability.controller;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.dto.ResponseDTO;
import com.aurora.observability.service.AgentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing agents within a specific project.
 * <p>
 * All endpoints are versioned under /{projectId}/v1/agents.
 * This controller provides endpoints for creating and retrieving agents.
 * </p>
 *
 * @author Malindu
 */
@RestController
@RequestMapping("{projectId}/v1/agents")
public class AgentController {

    private final AgentService agentService;

    /**
     * Constructs a new AgentController with the required AgentService.
     *
     * @param agentService the service layer used to perform agent operations
     */
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * Creates a new agent.
     * <p>
     * Requires the user to have the 'admin' role.
     * </p>
     *
     * @param projectId the UUID of the project to which the agent belongs (extracted from the URL path)
     * @param payload   the agent creation details (validated DTO)
     * @return a ResponseEntity containing a success message and HTTP status 201 (Created)
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping()
    public ResponseEntity<ResponseDTO> createAgent(@PathVariable UUID projectId,
                                                   @Valid @RequestBody CreateAgentRequestDTO payload) {
        agentService.createAgent(payload);

        ResponseDTO response = ResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .message("Agent Created Successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves all agents for a given project.
     * <p>
     * Requires the user to have the 'user' role.
     * </p>
     *
     * @param projectId the UUID of the project (extracted from the URL path)
     * @return a ResponseEntity containing the list of agents and HTTP status 200 (OK)
     */
    @PreAuthorize("hasRole('user')")
    @GetMapping()
    public ResponseEntity<ResponseDTO> getAllAgents(@PathVariable UUID projectId) {
        List<AgentResponseDTO> agents = agentService.getAllAgents();

        ResponseDTO response = ResponseDTO.builder()
                .data(agents)
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .message("All Agents Retrieved")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
