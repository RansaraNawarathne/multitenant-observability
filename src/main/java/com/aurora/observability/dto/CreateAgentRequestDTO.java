package com.aurora.observability.dto;

import com.aurora.observability.enums.AgentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAgentRequestDTO {
    private String name;
    private AgentType type;
    private Date expireDate;
}
