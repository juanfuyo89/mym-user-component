package com.mym.consulting.services;

import com.mym.consulting.entities.Contrato;
import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.model.request.SaveContractRequest;
import com.mym.consulting.repositories.ContractRepository;
import com.mym.consulting.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Contractservice {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public List<Contrato> getAllContracts(){
        List<Contrato> contractList = contractRepository.findAll();
        return contractList;
    }

    public void saveContract(SaveContractRequest saveContractRequest) {
        saveContractRequest.getContract().setStatus("A");
        contractRepository.save(saveContractRequest.getContract());
        if (saveContractRequest.getProjectWeightList() != null && !saveContractRequest.getProjectWeightList().isEmpty())
            saveContractRequest.getProjectWeightList().forEach(projectWeight -> {
                Proyecto project = this.projectRepository.getOne(projectWeight.getIdProject());
                project.setPeso(projectWeight.getWeight());
                this.projectRepository.save(project);
            });
    }

}
