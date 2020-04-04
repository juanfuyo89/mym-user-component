package com.mym.consulting.model.response;

import com.mym.consulting.entities.Contrato;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ContractResponse extends Response {
    public ContractResponse(String message, List<Contrato> contractList){
        super.setResponseMessage(message);
        this.contractList = contractList;
    }
    private List<Contrato> contractList;
}
