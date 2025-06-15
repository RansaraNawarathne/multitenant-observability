package com.aurora.observability.repository.implementation;

import com.aurora.observability.entity.Agent;
import com.aurora.observability.repository.AgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class AgentRepositoryImpl implements AgentRepository {

    private final EntityManager entityManager;

    @Override
    public void save(Agent agentData) {
        entityManager.persist(agentData);
    }

    @Override
    public Optional<Agent> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(Agent.class, id));
    }

    @Override
    public List<Agent> findAll() {
        TypedQuery<Agent> query = entityManager.createQuery("From Agent", Agent.class);

        return query.getResultList();
    }
}
