package com.mym.consulting.model.request;

import com.mym.consulting.entities.Pago;
import com.mym.consulting.entities.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavePaymentRequest {
    private Valor valor;
    private Pago pago;
}
