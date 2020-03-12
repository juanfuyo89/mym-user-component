package com.mym.consulting.model;

import com.mym.consulting.entities.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueResponse extends Response {
    public ValueResponse(String message, Valor valor){
        super.setResponseMessage(message);
        this.valor = valor;
    }
    private Valor valor;
}
