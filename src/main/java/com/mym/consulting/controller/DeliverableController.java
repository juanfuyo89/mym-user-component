package com.mym.consulting.controller;

import com.mym.consulting.entities.Entregable;
import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.model.response.DeliverableByProjectResponse;
import com.mym.consulting.model.response.DeliverableResponse;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.services.DeliverableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeliverableController extends GenericController{
    @Autowired
    private DeliverableService deliverableService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllDeliverables")
    public ResponseEntity<DeliverableResponse> getAllDeliverables(){
        logInfo("Inicia consulta de entregables: ");
        List<Entregable> deliverableList = new ArrayList<Entregable>();
        deliverableList = deliverableService.getAllDeliverables();
        return new ResponseEntity<DeliverableResponse>(new DeliverableResponse("Consulta exitosa", deliverableList), (deliverableList != null && !deliverableList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getDeliverablesByProject/{projectId}")
    public ResponseEntity<DeliverableByProjectResponse> getDeliverablesByProject(@PathVariable(required = true) Integer projectId){
        logInfo("Inicia consulta de entregables: ");
        List<EntregablesEtapa> deliverableStageList = deliverableService.getDeliverablesByProject(projectId);
        return new ResponseEntity<DeliverableByProjectResponse>(new DeliverableByProjectResponse("Consulta exitosa", deliverableStageList),
                (deliverableStageList != null && !deliverableStageList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveDeliverable")
    public ResponseEntity<Response> saveDeliverable(@RequestBody(required = true) Entregable deliverable){
        deliverableService.saveDeliverable(deliverable);
        logInfo("Guardando entregable: " + deliverable.getNombre());
        return new ResponseEntity<Response>(Response.getIntance("Entregable guardado exitosamente."), HttpStatus.OK);
    }

}
