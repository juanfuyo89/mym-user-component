package com.mym.consulting.model.response;

import com.mym.consulting.entities.Pago;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaymentsByProjectResponse extends Response {
    private List<Pago> paymentsByProject;
    public PaymentsByProjectResponse(String message, List<Pago> paymentsByProject) {
        super.setResponseMessage(message);
        this.paymentsByProject = paymentsByProject;
    }
}
