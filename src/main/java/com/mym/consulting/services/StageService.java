package com.mym.consulting.services;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    StageRepository stageRepository;

    public List<Etapa> getAllStages(){
        List<Etapa> allStages = stageRepository.findAll();
        return allStages;
    }

    public void addStage(Etapa etapa) {
        stageRepository.save(etapa);
    }

}
