package com.mym.consulting.model.request;

import com.mym.consulting.entities.Contrato;
import com.mym.consulting.model.dto.ProjectWeight;
import com.mym.consulting.model.dto.UserTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveContractRequest {
    private Contrato contract;
    private List<ProjectWeight> projectWeightList;
}
