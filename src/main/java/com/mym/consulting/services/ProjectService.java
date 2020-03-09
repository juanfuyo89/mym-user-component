package com.mym.consulting.services;

import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.repositories.ProjectRepository;
import com.mym.consulting.repositories.StagesProjectsRepository;
import com.mym.consulting.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ValueRepository valueRepository;
    @Autowired
    StagesProjectsRepository stagesProjectsRepository;

    @Transactional
    public void saveProject(SaveProjectRequest request) {
        Proyecto project = request.getProject();
        this.projectRepository.save(project);
        request.getValue().setIdProyecto(project.getId());
        this.valueRepository.save(request.getValue());
        request.getStagesProject().forEach(stage -> {
            EtapasProyecto etapasProyecto = new EtapasProyecto();
            stage.setIdProyecto(project.getId());
            etapasProyecto.setId(stage);
            this.stagesProjectsRepository.save(etapasProyecto);
        });
    }

    @Transactional
    public List<Proyecto> getProject() {
        return projectRepository.findAll();
    }

}
