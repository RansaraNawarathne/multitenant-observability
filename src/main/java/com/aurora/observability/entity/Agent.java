package com.aurora.observability.entity;

import com.aurora.observability.enums.AgentStatus;
import com.aurora.observability.enums.AgentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

/**
 * Represents an Agent entity in the observability system.
 *
 * <p>
 * Represents an Agent entity which can be of a specific type, such as a Linux VNC,
 * that can be utilized to perform specific tasks.
 * The creation and deployment of VNCs to a cluster are considered out of scope for this project,
 * hence such implementations are not included here.
 * </p>
 *
 * <p>
 * Each agent has a unique identifier, name, expiration date, and an optional URL endpoint.
 * It also maintains a status to indicate whether the agent is online, offline,
 * or in another operational state.
 * </p>
 *
 * <p>
 * This entity is mapped to a database table using JPA annotations.
 * </p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "agent")
public class Agent {

    /**
     * The unique identifier of the agent.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The human-readable name of the agent.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The type of the agent (e.g., LOCAL, CLUSTER).
     * Stored as a string in the database.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgentType type;

    /**
     * The expiration date of the agent.
     * Defines when the agent is considered invalid or inactive.
     */
    @Column(nullable = false)
    private Date expireDate;

    /**
     * The optional URL associated with the agent.
     * Can be used to connect or send data to this agent.
     */
    @Column
    private String url;

    /**
     * The operational status of the agent (e.g., ONLINE, OFFLINE).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgentStatus status;
}
