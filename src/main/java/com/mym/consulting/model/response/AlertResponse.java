package com.mym.consulting.model.response;

import com.mym.consulting.entities.Alerta;
import com.mym.consulting.entities.Cliente;

import java.util.List;

public class AlertResponse extends Response{
    public AlertResponse(String message, List<Alerta> alertsList){
        super.setResponseMessage(message);
        this.alertsList = alertsList;
    }
    private List<Alerta> alertsList;
}
