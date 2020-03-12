package com.mym.consulting.controller;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.model.Response;
import com.mym.consulting.model.StageResponse;
import com.mym.consulting.model.StagesByProjectResponse;
import com.mym.consulting.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StageController extends GenericController {

    @Autowired
    private StageService stageService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllStages")
    public ResponseEntity<StageResponse> getAllStages(){
        logInfo("Inicia consulta de etapas: ");
        List<Etapa> stageList = new ArrayList<Etapa>();
        stageList = stageService.getAllStages();
        return new ResponseEntity<StageResponse>(new StageResponse("Consulta exitosa", stageList),
                (stageList != null && !stageList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getStagesByProject/{projectId}")
    public ResponseEntity<StagesByProjectResponse> getStagesByProject(@PathVariable(required = true) Integer projectId){
        logInfo("Inicia consulta de etapas: ");
        List<EtapasProyecto> stagesProjectList = stageService.getStagesByProject(projectId);
        return new ResponseEntity<StagesByProjectResponse>(new StagesByProjectResponse("Consulta exitosa", stagesProjectList),
                (stagesProjectList != null && !stagesProjectList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveStage")
    public ResponseEntity<Response> saveStage(@RequestBody(required = true) Etapa stage){
        stageService.addStage(stage);
        logInfo("Guardando etapa: " + stage.getNombre());
        return new ResponseEntity<Response>(Response.getIntance("Etapa guardada exitosamente."), HttpStatus.OK);
    }

}
