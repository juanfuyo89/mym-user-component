package com.mym.consulting.services;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.FormaPago;
import com.mym.consulting.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public List<FormaPago> getAllPaymentMethods(){
        List<FormaPago> allPaymentMethods = paymentMethodRepository.findAll();
        return allPaymentMethods;
    }

}
