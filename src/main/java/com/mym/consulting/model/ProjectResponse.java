package com.mym.consulting.model;

import com.mym.consulting.entities.Entregable;
import com.mym.consulting.entities.Proyecto;

import java.util.List;

public class ProjectResponse extends Response{
    public ProjectResponse(String message, List<Proyecto> projectList){
        super.setResponseMessage(message);
        this.projectList = projectList;
    }

    private List<Proyecto> projectList;
}
