package com.mym.consulting.services;


import com.mym.consulting.entities.*;
import com.mym.consulting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DeliverableService {
    @Autowired
    DeliverableRepository deliverableRepository;
    @Autowired
    DeliverableStagesRepository deliverableStagesRepository;
    @Autowired
    AlertRepository alertRepository;
    @Autowired
    StageRepository stageRepository;
    @Autowired
    private ProjectRepository projectRepository;

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

    @Transactional
    public void updateDeliverableByProject(EntregablesEtapa deliverable) {
        EntregablesEtapa entregablesEtapa = deliverableStagesRepository.findById(deliverable.getId().getIdProyecto(),
                deliverable.getId().getIdEtapa(), deliverable.getId().getIdEntregable());
        if (entregablesEtapa != null){
            entregablesEtapa.setIdArchivo((deliverable.getIdArchivo() != null && deliverable.getIdArchivo() > 0)
                    ? deliverable.getIdArchivo() : entregablesEtapa.getIdArchivo());
            if ((deliverable.getEstado() != null && !deliverable.getEstado().isEmpty())) {
                entregablesEtapa.setEstado(deliverable.getEstado());
                if (deliverable.getEstado().equals("A")) {
                    Alerta alerta = new Alerta();
                    alerta.setTimestampAlerta(new Timestamp(System.currentTimeMillis()));
                    String deliverableName = deliverableRepository.findById(deliverable.getId().getIdEntregable()).get().getNombre();
                    String stageName = stageRepository.findById(deliverable.getId().getIdEtapa()).get().getNombre();
                    String projectCode = projectRepository.findById(deliverable.getId().getIdProyecto()).get().getCodigoProyecto();
                    alerta.setMensaje("Se actualizó el entregable " + deliverableName + ", de la entrega " + stageName
                            + ", del proyecto " + projectCode);
                    alerta.setLeida("N");
                    alertRepository.save(alerta);
                }
            }
            deliverableStagesRepository.save(entregablesEtapa);
        }
    }

    public void deleteDeliverableByProject(Integer projectId, Integer stageId, Integer deliverableId) {
        EntregablesEtapa entregablesEtapa = deliverableStagesRepository.findById(projectId, stageId, deliverableId);
        if(entregablesEtapa != null)
            deliverableStagesRepository.delete(entregablesEtapa);
    }

}
