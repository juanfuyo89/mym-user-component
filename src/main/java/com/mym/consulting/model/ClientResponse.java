package com.mym.consulting.model;

import com.mym.consulting.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientResponse extends Response{
    public ClientResponse(String message, List<Cliente> clientList){
        super.setResponseMessage(message);
        this.clientList = clientList;
    }
    private List<Cliente> clientList;
}
