package com.mym.consulting.controller;

import com.mym.consulting.model.Response;
import com.mym.consulting.model.SaveProjectRequest;
import com.mym.consulting.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
