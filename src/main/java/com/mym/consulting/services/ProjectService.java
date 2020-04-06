package com.mym.consulting.services;

import com.mym.consulting.entities.*;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ValueRepository valueRepository;
    @Autowired
    private StagesProjectsRepository stagesProjectsRepository;
    @Autowired
    private DeliverableStagesRepository deliverableStagesRepository;
    @Autowired
    private ContractRepository contractRepository;

    public void saveProject(SaveProjectRequest request) {
        Proyecto project = request.getProject();
        Contrato contrato = this.contractRepository.findById(project.getContrato().getId()).get();
        project.setContrato(contrato);
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

    public List<Proyecto> getAllProjects() {
        return projectRepository.findAll();
    }

    public Valor getValueByProject(Integer projectId) {
        return valueRepository.findByIdProject(projectId);
    }
}
