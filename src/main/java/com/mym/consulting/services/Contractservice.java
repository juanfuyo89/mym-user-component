package com.mym.consulting.services;

import com.mym.consulting.entities.Contrato;
import com.mym.consulting.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Contractservice {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contrato> getAllContracts(){
        List<Contrato> contractList = contractRepository.findAll();
        return contractList;
    }

    public void saveContract(Contrato contract) {
        contract.setStatus("A");
        contractRepository.save(contract);
    }

}
