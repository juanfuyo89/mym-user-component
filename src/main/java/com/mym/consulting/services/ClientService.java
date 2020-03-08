package com.mym.consulting.services;

import com.mym.consulting.entities.Cliente;
import com.mym.consulting.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Cliente> getAllClients(){
        return clientRepository.findAll();
    }

    public void saveClient(Cliente client) {
        clientRepository.save(client);
    }

}
