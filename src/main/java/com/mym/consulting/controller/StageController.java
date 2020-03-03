package com.mym.consulting.controller;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.model.Response;
import com.mym.consulting.model.StageResponse;
import com.mym.consulting.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StageController extends GenericController{

    @Autowired
    StageService stageService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllStages")
    public ResponseEntity<StageResponse> getAllClients(){
        logInfo("Inicia consulta de etapas: ");
        List<Etapa> stageList = new ArrayList<Etapa>();
        stageList = stageService.getAllStages();
        return new ResponseEntity<StageResponse>(new StageResponse("Consulta exitosa", stageList),
                (stageList != null && !stageList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveStage")
    public ResponseEntity<Response> saveProject(@RequestBody(required = true) Etapa etapa){
        stageService.addStage(etapa);
        logInfo("Guardando etapa: " + etapa.getNombre());
        return new ResponseEntity<Response>(Response.getIntance("Etapa guardada exitosamente."), HttpStatus.OK);
    }

}
