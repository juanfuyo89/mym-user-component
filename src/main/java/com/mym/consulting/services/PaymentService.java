package com.mym.consulting.services;

import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.entities.Pago;
import com.mym.consulting.entities.Valor;
import com.mym.consulting.model.request.SavePaymentRequest;
import com.mym.consulting.repositories.PaymentRepository;
import com.mym.consulting.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ValueRepository valueRepository;

    public void savePayment(SavePaymentRequest paymentRequest) {
        Valor valor = valueRepository.findByIdProject(paymentRequest.getValor().getIdProyecto());
        Pago pago = (paymentRequest.getPago().getId() != null && paymentRequest.getPago().getId() > 0)
                ? paymentRepository.findById(paymentRequest.getPago().getId()).get() : new Pago();
        pago.setIdValor(valor.getId());
        pago.setFecha(new Date());
        pago.setValorPago(paymentRequest.getPago().getValorPago());
        paymentRepository.save(pago);
    }

    public List<Pago> getPaymentsByProject(Integer projectId){
        List<Pago> paymentsByProject = new ArrayList<>();
        Valor valor = valueRepository.findByIdProject(projectId);
        paymentsByProject = paymentRepository.findByIdValue(valor.getId());
        return paymentsByProject;
    }

    public void deletePayment(Integer paymentId) {
        Optional<Pago> optional = paymentRepository.findById(paymentId);
        if(optional.isPresent())
            paymentRepository.delete(optional.get());
    }

}
