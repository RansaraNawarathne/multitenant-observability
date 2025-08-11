package com.aurora.observability.dto;

import com.aurora.observability.enums.AgentStatus;
import com.aurora.observability.enums.AgentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the details of an agent in the observability system.
 * <p>
 * This class is typically used as a response model when returning agent data from APIs.
 * It encapsulates metadata such as the agent's identity, type, status, and expiration details.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 *     AgentResponseDTO agent = AgentResponseDTO.builder()
 *         .id(UUID.randomUUID())
 *         .name("TelemetryAgent-001")
 *         .type(AgentType.TELEMETRY)
 *         .expireDate(new Date())
 *         .url("https://agent.example.com")
 *         .status(AgentStatus.ACTIVE)
 *         .build();
 * </pre>
 *
 * @see AgentType
 * @see AgentStatus
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentResponseDTO {

    /**
     * Unique identifier of the agent.
     */
    private UUID id;

    /**
     * Human-readable name of the agent.
     */
    private String name;

    /**
     * The functional category or role of the agent.
     */
    private AgentType type;

    /**
     * The date and time when the agent's validity or subscription expires.
     */
    private Date expireDate;

    /**
     * The network endpoint (URL) used to communicate with the agent.
     */
    private String url;

    /**
     * The current operational status of the agent.
     */
    private AgentStatus status;
}
