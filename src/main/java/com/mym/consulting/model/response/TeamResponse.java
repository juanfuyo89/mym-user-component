package com.mym.consulting.model.response;

import com.mym.consulting.entities.Equipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse extends Response {
    public TeamResponse(String message, List<Equipo> teamList){
        super.setResponseMessage(message);
        this.teamList = teamList;
    }
    private List<Equipo> teamList;
}
