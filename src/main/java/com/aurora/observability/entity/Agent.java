package com.aurora.observability.entity;

import com.aurora.observability.enums.AgentStatus;
import com.aurora.observability.enums.AgentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgentType type;

    @Column(nullable = false)
    private Date expireDate;

    @Column(nullable = true)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgentStatus status;
}
