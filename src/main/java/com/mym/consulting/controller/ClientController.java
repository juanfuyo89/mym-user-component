package com.mym.consulting.controller;

import com.mym.consulting.entities.Cliente;
import com.mym.consulting.model.ClientResponse;
import com.mym.consulting.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController extends GenericController{
    @Autowired
    ClientService clientService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllClients")
    public ResponseEntity<ClientResponse> getAllClients(){
        logInfo("Inicia consulta de clientes: ");
        List<Cliente> clientList = new ArrayList<Cliente>();
        clientList = clientService.getAllClients();
        return new ResponseEntity<ClientResponse>(new ClientResponse("Consulta exitosa", clientList), (clientList != null && !clientList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
