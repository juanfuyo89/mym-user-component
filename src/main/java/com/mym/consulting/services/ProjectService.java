package com.mym.consulting.services;

import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.entities.Valor;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.repositories.DeliverableStagesRepository;
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
    private ProjectRepository projectRepository;
    @Autowired
    private ValueRepository valueRepository;
    @Autowired
    private StagesProjectsRepository stagesProjectsRepository;
    @Autowired
    private DeliverableStagesRepository deliverableStagesRepository;

    @Transactional
    public void saveProject(SaveProjectRequest request) {
        Proyecto project = request.getProject();
        this.projectRepository.save(project);
        request.getValue().setIdProyecto(project.getId());
        Valor value = this.valueRepository.findByIdProject(project.getId());
        if (value != null && value.getTotal() > 0) {
            value.setTotal(request.getValue().getTotal());
        } else {
            value = request.getValue();
        }
        this.valueRepository.save(value);
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

    public List<Proyecto> getProject() {
        return projectRepository.findAll();
    }

    public Valor getValueByProject(Integer projectId) {
        return valueRepository.findByIdProject(projectId);
    }
}
