package com.mym.consulting.services;

import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.model.response.ProjectAdvanceResponse;
import com.mym.consulting.repositories.DeliverableStagesRepository;
import com.mym.consulting.repositories.StagesProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartService {

    @Autowired
    StagesProjectsRepository stagesProjectsRepository;
    @Autowired
    DeliverableStagesRepository deliverableStagesRepository;

    private Double acumPercent = 0.0;
    private String lastDate = "";

    public ProjectAdvanceResponse getAdvancedChartByProject(Integer projectId) {
        ProjectAdvanceResponse projectAdvanceResponse = new ProjectAdvanceResponse();
        List<EtapasProyecto> etapasProyectos = stagesProjectsRepository.findByIdProject(projectId);
        List<EntregablesEtapa> entregablesEtapas = deliverableStagesRepository.findByIdProjectEnd(projectId, "F");
        List<String> labelList = new ArrayList<>();
        List<Double> dataList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        etapasProyectos.forEach(etapasProyecto -> {
            entregablesEtapas.forEach(entregablesEtapa -> {
                if (entregablesEtapa.getId().getIdEtapa() == etapasProyecto.getId().getIdEtapa()) {
                    double percent = ((entregablesEtapa.getPeso()/100.0)*etapasProyecto.getPeso());
                    String strDate = dateFormat.format(entregablesEtapa.getFechaEntrega());
                    if (strDate.equals(lastDate) && dataList.size() > 0) {
                        double temp = dataList.get(dataList.size()-1);
                        dataList.remove(dataList.size()-1);
                    } else {
                        labelList.add(strDate);
                    }
                    dataList.add(percent + acumPercent);
                    acumPercent += percent;
                    lastDate = strDate;
                }
            });
        });
        acumPercent = 0.0;
        projectAdvanceResponse.setLabelList(labelList);
        projectAdvanceResponse.setDataList(dataList);
        return projectAdvanceResponse;
    }

}
