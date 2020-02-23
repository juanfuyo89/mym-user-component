package com.mym.consulting.model;

import com.mym.consulting.entities.EtapasProyectoPK;
import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.entities.Valor;
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
    private List<EtapasProyectoPK> stagesProject;
}
