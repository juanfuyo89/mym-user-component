package com.mym.consulting.model.response;

import com.mym.consulting.entities.Etapa;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StageResponse extends Response {
    public StageResponse(String message, List<Etapa> stagesList){
        super.setResponseMessage(message);
        this.stagesList = stagesList;
    }
    private List<Etapa> stagesList;
}
