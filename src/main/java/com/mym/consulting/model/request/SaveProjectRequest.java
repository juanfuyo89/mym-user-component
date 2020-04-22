package com.mym.consulting.model.request;

import com.mym.consulting.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveProjectRequest {
    private Proyecto project;
    private Valor value;
    private List<EtapasProyectoPK> stageProjectList;
    private List<EntregablesEtapa> deliverableStageList;
}
