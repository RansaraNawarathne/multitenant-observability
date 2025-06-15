package com.aurora.observability.repository;

import com.aurora.observability.entity.Agent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgentRepository {
    public void save (Agent agentData);
    public Optional<Agent> findById (UUID id);
    public List<Agent> findAll ();
}
