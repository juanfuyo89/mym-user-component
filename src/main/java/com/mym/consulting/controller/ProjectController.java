package com.mym.consulting.controller;


import com.mym.consulting.entities.Proyecto;
import com.mym.consulting.entities.Valor;
import com.mym.consulting.model.*;
import com.mym.consulting.model.response.ProjectResponse;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.model.response.ValueResponse;
import com.mym.consulting.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController extends GenericController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST, path = "/saveProject")
    public ResponseEntity<Response> saveProject(@RequestBody(required = true) SaveProjectRequest request){
        projectService.saveProject(request);
        logInfo("Guardando proyecto: " + request.getProject().getCodigoProyecto());
        return new ResponseEntity<Response>(Response.getIntance("Proyecto guardado exitosamente."), HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllProjects")
    public ResponseEntity<ProjectResponse> getAllProjects() {
        logInfo("Inicia consulta de proyectos: ");
        List<Proyecto> projectList = projectService.getAllProjects();
        return new ResponseEntity<ProjectResponse>(new ProjectResponse("Consulta exitosa", projectList), (projectList != null && !projectList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getValueByProject/{projectId}")
    public ResponseEntity<ValueResponse> getValueByProject(@PathVariable(required = true) Integer projectId) {
        logInfo("Inicia consulta de valor del proyecto ");
        Valor valor = projectService.getValueByProject(projectId);
        return new ResponseEntity<ValueResponse>(new ValueResponse("Consulta exitosa", valor), (valor != null && valor.getIdProyecto() != 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
