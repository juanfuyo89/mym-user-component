package com.mym.consulting.controller;

import com.mym.consulting.model.response.ProjectAdvanceResponse;
import com.mym.consulting.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController extends GenericController {

    @Autowired
    private ChartService chartService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAdvancedChartByProject/{projectId}")
    public ResponseEntity<ProjectAdvanceResponse> getAdvancedChartByProject(@PathVariable(required = true) Integer projectId) {
        ProjectAdvanceResponse projectAdvanceResponse = chartService.getAdvancedChartByProject(projectId);
        return new ResponseEntity<ProjectAdvanceResponse>(projectAdvanceResponse, HttpStatus.OK);
    }

}
