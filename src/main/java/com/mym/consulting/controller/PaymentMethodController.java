package com.mym.consulting.controller;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.FormaPago;
import com.mym.consulting.model.PaymentMethodResponse;
import com.mym.consulting.model.Response;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.model.StageResponse;
import com.mym.consulting.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentMethodController extends GenericController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllPaymentMethods")
    public ResponseEntity<PaymentMethodResponse> getAllPaymentMethods() {
        logInfo("Inicia consulta de etapas: ");
        List<FormaPago> paymentMethodList = paymentMethodService.getAllPaymentMethods();
        return new ResponseEntity<PaymentMethodResponse>(new PaymentMethodResponse("Consulta exitosa", paymentMethodList),
                (paymentMethodList != null && !paymentMethodList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/savePaymentMethod")
    public ResponseEntity<Response> savePaymentMethod(@RequestBody(required = true) FormaPago request){
        paymentMethodService.savePaymentMethod(request);
        logInfo("Guardando forma de pago: " + request.getDescripcion());
        return new ResponseEntity<Response>(Response.getIntance("Forma de pago guardada exitosamente."), HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/deletePaymentMethod/{paymentMethodId}")
    public ResponseEntity<Response> deletePaymentMethod(@PathVariable(required = true) Integer paymentMethodId) {
        logInfo("Eliminando forma de pago: " + paymentMethodId);
        paymentMethodService.deletePaymentMethod(paymentMethodId);
        return new ResponseEntity<Response>(Response.getIntance("Forma de pago eliminada exitosamente."), HttpStatus.OK);
    }

}
