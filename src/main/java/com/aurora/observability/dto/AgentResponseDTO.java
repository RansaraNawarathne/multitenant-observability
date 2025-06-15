package com.aurora.observability.dto;

import com.aurora.observability.enums.AgentStatus;
import com.aurora.observability.enums.AgentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentResponseDTO {
    private UUID id;
    private String name;
    private AgentType type;
    private Date expireDate;
    private String url;
    private AgentStatus status;
}
