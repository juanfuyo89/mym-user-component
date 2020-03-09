package com.mym.consulting.controller;


import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.model.DeliverableResponse;
import com.mym.consulting.model.ProjectResponse;
import com.mym.consulting.model.Response;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController extends GenericController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveProject")
    public ResponseEntity<Response> saveProject(@RequestBody(required = true) SaveProjectRequest request){
        projectService.saveProject(request);
        logInfo("Guardando proyecto: " + request.getProject().getNombre());
        return new ResponseEntity<Response>(Response.getIntance("Proyecto guardado exitosamente."), HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/getAllProjects")
    public ResponseEntity<ProjectResponse> getProject(@RequestBody(required = true) SaveProjectRequest request){
        logInfo("Inicia consulta de proyectos: ");
        List<Proyecto> projectList = new ArrayList<Proyecto>();
        projectList = projectService.getProject();
        return new ResponseEntity<ProjectResponse>(new ProjectResponse("Consulta exitosa", projectList), (projectList != null && !projectList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
