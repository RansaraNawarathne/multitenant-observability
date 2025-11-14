package com.aurora.observability.repository;

import com.aurora.observability.entity.Agent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for performing CRUD operations on {@link Agent} entities.
 * <p>
 * This abstraction defines the contract for saving and retrieving agent records,
 * allowing different persistence implementations (e.g., JPA, JDBC, in-memory)
 * to provide the actual logic.
 */
public interface AgentRepository {

    /**
     * Persists the given {@link Agent} entity.
     *
     * @param agentData the agent entity to save; must not be {@code null}
     */
    void save(Agent agentData);

    /**
     * Retrieves an {@link Agent} by its unique identifier.
     *
     * @param id the UUID of the agent; must not be {@code null}
     * @return an {@link Optional} containing the found agent, or an empty Optional if no agent exists with the given ID
     */
    Optional<Agent> findById(UUID id);

    /**
     * Retrieves all {@link Agent} entities.
     *
     * @return a list containing all agents; never {@code null}
     */
    List<Agent> findAll();
}
