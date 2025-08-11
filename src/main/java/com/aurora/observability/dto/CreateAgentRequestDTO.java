package com.aurora.observability.dto;

import com.aurora.observability.enums.AgentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object (DTO) used for creating a new agent in the observability system.
 * <p>
 * This class is typically used as the request payload in API calls that
 * create and register a new agent. It contains the necessary metadata
 * to define the agent's identity, type, and expiration details.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 *     CreateAgentRequestDTO request = CreateAgentRequestDTO.builder()
 *         .name("TelemetryAgent-001")
 *         .type(AgentType.TELEMETRY)
 *         .expireDate(new Date())
 *         .build();
 * </pre>
 *
 * @see AgentType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAgentRequestDTO {

    /**
     * Human-readable name of the agent to be created.
     */
    private String name;

    /**
     * The functional category or role of the agent.
     */
    private AgentType type;

    /**
     * The date and time when the agent's validity or subscription will expire.
     */
    private Date expireDate;
}
