package com.mym.consulting.model.response;

import com.mym.consulting.entities.EntregablesEtapa;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeliverableByProjectResponse extends Response {
    public DeliverableByProjectResponse(String message, List<EntregablesEtapa> deliverableProjectList){
        super.setResponseMessage(message);
        this.deliverableStageList = deliverableProjectList;
    }
    private List<EntregablesEtapa> deliverableStageList;
}
