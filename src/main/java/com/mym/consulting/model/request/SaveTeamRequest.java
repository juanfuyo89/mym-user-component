package com.mym.consulting.model.request;

import com.mym.consulting.entities.Equipo;
import com.mym.consulting.model.dto.UserTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveTeamRequest {
    private Equipo team;
    private List<UserTeam> userTeamList;
}
