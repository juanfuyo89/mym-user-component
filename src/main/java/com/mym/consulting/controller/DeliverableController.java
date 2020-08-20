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

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/updateDeliverableByProject")
    public ResponseEntity<Response> updateDeliverableByProject(@RequestBody(required = true) EntregablesEtapa deliverable){
        logInfo("Guardando entregable: " + deliverable.getId().getIdEntregable());
        deliverableService.updateDeliverableByProject(deliverable);
        return new ResponseEntity<Response>(Response.getIntance("Entregable actualizado exitosamente."), HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET,
            path = "/deleteDeliverableByProject/{projectId}/{stageId}/{deliverableId}")
    public ResponseEntity<Response> deleteDeliverableByProject(@PathVariable(required = true) Integer projectId,
                                                               @PathVariable(required = true) Integer stageId,
                                                               @PathVariable(required = true) Integer deliverableId){
        deliverableService.deleteDeliverableByProject(projectId, stageId, deliverableId);
        logInfo("Eliminando entregable del proyecto con ID: " + projectId);
        return new ResponseEntity<Response>(Response.getIntance("Entregable eliminado exitosamente."), HttpStatus.OK);
    }

}
