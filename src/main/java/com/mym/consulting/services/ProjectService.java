package com.mym.consulting.services;

import com.mym.consulting.entities.*;
import com.mym.consulting.model.request.SaveProjectRequest;
import com.mym.consulting.model.request.SaveContractRequest;
import com.mym.consulting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Contrato contract = this.contractRepository.findById(project.getContrato().getId()).get();
        project.setContrato(contract);
        this.setWeight(project, contract);
        this.projectRepository.save(project);
        request.getValue().setIdProyecto(project.getId());
        this.saveValue(request.getValue(), project.getId());
        this.saveStagesProject(request.getStageProjectList(), project.getId());
        this.saveDeliverableStage(request.getDeliverableStageList(), project.getId());
    }

    public List<Proyecto> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Proyecto> getProjectsByContract(Integer contractId) {
        return projectRepository.findByContract(contractId);
    }

    public Valor getValueByProject(Integer projectId) {
        return valueRepository.findByIdProject(projectId);
    }

    private void setWeight(Proyecto project, Contrato contract) {
        if (project.getId() != null && project.getId() > 0){
            project.setPeso(this.projectRepository.getOne(project.getId()).getPeso());
        } else {
            List<Proyecto> projectsByContract = this.projectRepository.findByContract(contract.getId());
            project.setPeso((projectsByContract != null && !projectsByContract.isEmpty()) ? 0 : 100);
        }
    }

    private void saveValue(Valor newValue, Integer projectId) {
        Valor value = this.valueRepository.findByIdProject(projectId);
        if (value != null && value.getTotal() > 0) {
            value.setTotal(newValue.getTotal());
        } else {
            value = newValue;
        }
        this.valueRepository.save(value);
    }

    private void saveStagesProject(List<EtapasProyecto> stagesProjectList, Integer projectId) {
        stagesProjectList.forEach(stage -> {
            EtapasProyecto etapasProyecto = this.stagesProjectsRepository.findById(projectId, stage.getId().getIdEtapa());
            if (etapasProyecto == null) {
                etapasProyecto = new EtapasProyecto();
            }
            stage.getId().setIdProyecto(projectId);
            etapasProyecto.setId(stage.getId());
            etapasProyecto.setPeso(stage.getPeso());
            this.stagesProjectsRepository.save(etapasProyecto);
        });
    }

    private void saveDeliverableStage(List<EntregablesEtapa> deliverableStageList, Integer projectId) {
        deliverableStageList.forEach(deliverable -> {
            deliverable.getId().setIdProyecto(projectId);
            EntregablesEtapa deliverableStages =
                    this.deliverableStagesRepository.findById(deliverable.getId().getIdProyecto(),
                            deliverable.getId().getIdEtapa(), deliverable.getId().getIdEntregable());
            if (deliverableStages == null) {
                deliverableStages = new EntregablesEtapa();
                deliverableStages.setId(deliverable.getId());
                deliverableStages.setPeso(deliverable.getPeso());
                deliverableStages.setEstado("I");
            } else
                deliverableStages.setPeso(deliverable.getPeso());
            this.deliverableStagesRepository.save(deliverableStages);
        });
    }

}
