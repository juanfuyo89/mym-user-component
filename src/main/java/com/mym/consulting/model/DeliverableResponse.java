package com.mym.consulting.model;

import com.mym.consulting.entities.Ciudad;
import com.mym.consulting.entities.Entregable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverableResponse extends Response{
    public DeliverableResponse(String message, List<Entregable> deliverableList){
        super.setResponseMessage(message);
        this.deliverableList = deliverableList;
    }

    private List<Entregable> deliverableList;
}
