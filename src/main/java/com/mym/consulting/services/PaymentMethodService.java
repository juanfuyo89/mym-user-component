package com.mym.consulting.services;

import com.mym.consulting.entities.*;
import com.mym.consulting.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public void savePaymentMethod(FormaPago formaPago) {
        this.paymentMethodRepository.save(formaPago);
    }

    public List<FormaPago> getAllPaymentMethods(){
        List<FormaPago> allPaymentMethods = paymentMethodRepository.findAll();
        return allPaymentMethods;
    }

    @Transactional
    public void deletePaymentMethod(Integer paymentMethodId) {
        Optional<FormaPago> optional = this.paymentMethodRepository.findById(paymentMethodId);
        if (optional != null && optional.isPresent()) {
            FormaPago paymentMethod = optional.get();
            this.paymentMethodRepository.delete(paymentMethod);
        }
    }

}
