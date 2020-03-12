package com.mym.consulting.services;


import com.mym.consulting.entities.Entregable;
import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.repositories.DeliverableRepository;
import com.mym.consulting.repositories.DeliverableStagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverableService {
    @Autowired
    DeliverableRepository deliverableRepository;
    @Autowired
    DeliverableStagesRepository deliverableStagesRepository;

    public List<Entregable> getAllDeliverables(){
        return deliverableRepository.findAll();
    }

    public List<EntregablesEtapa> getDeliverablesByProject(Integer projectId){
        List<EntregablesEtapa> allStagesByProject = deliverableStagesRepository.findByIdProject(projectId);
        return allStagesByProject;
    }

    public void saveDeliverable(Entregable deliverable) {
        deliverableRepository.save(deliverable);
    }
}
