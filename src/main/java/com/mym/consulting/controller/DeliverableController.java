package com.mym.consulting.controller;

import com.mym.consulting.entities.Entregable;
import com.mym.consulting.entities.Etapa;
import com.mym.consulting.model.DeliverableResponse;
import com.mym.consulting.model.Response;
import com.mym.consulting.services.DeliverableService;
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
public class DeliverableController extends GenericController{
    @Autowired
    DeliverableService deliverableService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllDeliverables")
    public ResponseEntity<DeliverableResponse> getAllDeliverables(){
        logInfo("Inicia consulta de entregables: ");
        List<Entregable> DeliverableList = new ArrayList<Entregable>();
        DeliverableList = deliverableService.getAllDeliverables();
        return new ResponseEntity<DeliverableResponse>(new DeliverableResponse("Consulta exitosa", DeliverableList), (DeliverableList != null && !DeliverableList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveDeliverable")
    public ResponseEntity<Response> saveDeliverable(@RequestBody(required = true) Entregable deliverable){
        deliverableService.saveDeliverable(deliverable);
        logInfo("Guardando entregable: " + deliverable.getNombre());
        return new ResponseEntity<Response>(Response.getIntance("Entregable guardado exitosamente."), HttpStatus.OK);
    }

}
