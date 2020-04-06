package com.mym.consulting.controller;

import com.mym.consulting.entities.Contrato;
import com.mym.consulting.entities.Etapa;
import com.mym.consulting.model.response.ContractResponse;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.services.Contractservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ContractController extends GenericController {

    @Autowired
    Contractservice contractservice;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllContracts")
    public ResponseEntity<ContractResponse> getAllContracts(){
        logInfo("Inicia consulta de contratos: ");
        List<Contrato> contractList = contractservice.getAllContracts();
        return new ResponseEntity<ContractResponse>(new ContractResponse("Consulta exitosa", contractList),
                (contractList != null && !contractList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveContract")
    public ResponseEntity<Response> saveContract(@RequestBody(required = true) Contrato contract){
        contractservice.saveContract(contract);
        logInfo("Guardando contrato N°: " + contract.getNumeroContrato());
        return new ResponseEntity<Response>(Response.getIntance("Contrato guardado exitosamente."), HttpStatus.OK);
    }

}
