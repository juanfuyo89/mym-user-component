package com.mym.consulting.model.response;

import com.mym.consulting.entities.EtapasProyecto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StagesByProjectResponse extends Response {
    public StagesByProjectResponse(String message, List<EtapasProyecto> stagesProjectList){
        super.setResponseMessage(message);
        this.stagesProjectList = stagesProjectList;
    }
    private List<EtapasProyecto> stagesProjectList;
}
