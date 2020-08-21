package com.mym.consulting.controller;

import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.entities.Pago;
import com.mym.consulting.model.request.SavePaymentRequest;
import com.mym.consulting.model.response.DeliverableByProjectResponse;
import com.mym.consulting.model.response.PaymentsByProjectResponse;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController extends GenericController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/savePayment")
    public ResponseEntity<Response> savePayment(@RequestBody(required = true) SavePaymentRequest paymentRequest){
        paymentService.savePayment(paymentRequest);
        logInfo("Guardando pago para el proyecto: " + paymentRequest.getValor().getIdProyecto());
        return new ResponseEntity<Response>(Response.getIntance("Pago guardado exitosamente."), HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getPaymentsByProject/{projectId}")
    public ResponseEntity<PaymentsByProjectResponse> getPaymentsByProject(@PathVariable(required = true) Integer projectId) {
        logInfo("Inicia consulta de entregables: ");
        List<Pago> paymentsByProject = paymentService.getPaymentsByProject(projectId);
        return new ResponseEntity<PaymentsByProjectResponse>(new PaymentsByProjectResponse("Consulta exitosa", paymentsByProject),
                HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET,
            path = "/deletePayment/{paymentId}")
    public ResponseEntity<Response> deletePayment(@PathVariable(required = true) Integer paymentId) {
        paymentService.deletePayment(paymentId);
        logInfo("Eliminando pago con Id: " + paymentId);
        return new ResponseEntity<Response>(Response.getIntance("Pago eliminado exitosamente."), HttpStatus.OK);
    }

}
