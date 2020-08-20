package com.mym.consulting.services;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.repositories.DeliverableStagesRepository;
import com.mym.consulting.repositories.StageRepository;
import com.mym.consulting.repositories.StagesProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StageService {

    @Autowired
    StageRepository stageRepository;
    @Autowired
    StagesProjectsRepository stagesProjectsRepository;
    @Autowired
    DeliverableStagesRepository deliverableStagesRepository;

    public List<Etapa> getAllStages(){
        List<Etapa> allStages = stageRepository.findAll();
        return allStages;
    }

    public List<EtapasProyecto> getStagesByProject(Integer projectId){
        List<EtapasProyecto> allStagesByProject = stagesProjectsRepository.findByIdProject(projectId);
        return allStagesByProject;
    }

    public void addStage(Etapa etapa) {
        stageRepository.save(etapa);
    }

    @Transactional
    public void deleteStageByProject(Integer projectId, Integer stageId) {
        EtapasProyecto etapasProyecto = stagesProjectsRepository.findById(projectId, stageId);
        deliverableStagesRepository.deleteByStage(projectId, stageId);
        if (etapasProyecto != null)
            stagesProjectsRepository.delete(etapasProyecto);
    }

}
