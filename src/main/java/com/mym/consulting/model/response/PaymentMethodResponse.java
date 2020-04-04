package com.mym.consulting.model.response;

import com.mym.consulting.entities.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaymentMethodResponse extends Response {
    public PaymentMethodResponse(String message, List<FormaPago> paymentMethodList){
        super.setResponseMessage(message);
        this.paymentMethodList = paymentMethodList;
    }
    private List<FormaPago> paymentMethodList;
}
