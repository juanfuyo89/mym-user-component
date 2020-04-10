package com.mym.consulting.controller;

import com.mym.consulting.entities.Alerta;
import com.mym.consulting.model.response.AlertResponse;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.services.AlertService;
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
public class AlertController extends GenericController{
    @Autowired
    AlertService alertService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllAlerts")
    public ResponseEntity<AlertResponse> getAllAlerts() {
        logInfo("Inicia consulta de Alertas: ");
        List<Alerta> alertsList = new ArrayList<Alerta>();
        alertsList = alertService.getAllAlerts();
        alertsList.forEach(alerta -> {
            System.out.println(alerta.getMensaje());
        });
        return new ResponseEntity<AlertResponse>(new AlertResponse("Consulta exitosa", alertsList), (alertsList != null && !alertsList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/generateAlert")
    public ResponseEntity<Response> generateAlert(@RequestBody(required = true) Alerta alert){
         alertService.generateAlert(alert);
        return new ResponseEntity<Response>(Response.getIntance("Alerta creada exitosamente."), HttpStatus.OK);
    }
}
