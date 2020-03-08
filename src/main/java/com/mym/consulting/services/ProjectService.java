package com.mym.consulting.services;

import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.repositories.DeliverableStagesRepository;
import com.mym.consulting.repositories.ProjectRepository;
import com.mym.consulting.repositories.StagesProjectsRepository;
import com.mym.consulting.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ValueRepository valueRepository;
    @Autowired
    StagesProjectsRepository stagesProjectsRepository;
    @Autowired
    DeliverableStagesRepository deliverableStagesRepository;

    @Transactional
    public void saveProject(SaveProjectRequest request) {
        Proyecto project = request.getProject();
        this.projectRepository.save(project);
        request.getValue().setIdProyecto(project.getId());
        this.valueRepository.save(request.getValue());
        request.getStageProjectList().forEach(stage -> {
            EtapasProyecto etapasProyecto = new EtapasProyecto();
            stage.setIdProyecto(project.getId());
            etapasProyecto.setId(stage);
            this.stagesProjectsRepository.save(etapasProyecto);
        });
        request.getDeliverableStageList().forEach(deliverable -> {
            EntregablesEtapa entregablesEtapa = new EntregablesEtapa();
            deliverable.setIdProyecto(project.getId());
            entregablesEtapa.setId(deliverable);
            this.deliverableStagesRepository.save(entregablesEtapa);
        });
    }

}
