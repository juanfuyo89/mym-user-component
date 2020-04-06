package com.mym.consulting.model.response;

import com.mym.consulting.entities.Proyecto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectResponse extends Response{
    public ProjectResponse(String message, List<Proyecto> projectList){
        super.setResponseMessage(message);
        this.projectList = projectList;
    }

    private List<Proyecto> projectList;
}
