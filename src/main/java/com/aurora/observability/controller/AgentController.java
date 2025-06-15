package com.aurora.observability.controller;

import com.aurora.observability.dto.AgentResponseDTO;
import com.aurora.observability.dto.CreateAgentRequestDTO;
import com.aurora.observability.dto.ResponseDTO;
import com.aurora.observability.service.AgentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("{projectId}/v1/agents")
public class AgentController {

    AgentService agentService;

    public AgentController (AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createAgent (@PathVariable UUID projectId, @Valid @RequestBody CreateAgentRequestDTO payload) {
        agentService.createAgent(payload);

        ResponseDTO response = ResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .message("Agent Created Successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAllAgents (@PathVariable UUID projectId) {
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
