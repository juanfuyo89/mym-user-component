package com.mym.consulting.services;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.repositories.StageRepository;
import com.mym.consulting.repositories.StagesProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    StageRepository stageRepository;
    @Autowired
    StagesProjectsRepository stagesProjectsRepository;

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

}
