package com.mym.consulting.controller;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.FormaPago;
import com.mym.consulting.model.PaymentMethodResponse;
import com.mym.consulting.model.StageResponse;
import com.mym.consulting.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
