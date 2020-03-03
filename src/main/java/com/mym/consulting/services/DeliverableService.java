package com.mym.consulting.services;


import com.mym.consulting.entities.Entregable;
import com.mym.consulting.repositories.DeliverableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverableService {
    @Autowired
    DeliverableRepository deliverableRepository;

    public List<Entregable> getAllDeliverables(){
        return deliverableRepository.findAll();
    }
}
