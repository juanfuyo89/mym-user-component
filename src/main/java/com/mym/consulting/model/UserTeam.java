package com.mym.consulting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTeam {
    private Integer userId;
    private Integer teamId;
    private Boolean isLeader;
}
