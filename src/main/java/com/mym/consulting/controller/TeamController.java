package com.mym.consulting.controller;

import com.mym.consulting.entities.Equipo;
import com.mym.consulting.entities.Etapa;
import com.mym.consulting.model.StageResponse;
import com.mym.consulting.model.TeamResponse;
import com.mym.consulting.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController extends GenericController {

    @Autowired
    TeamService teamService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllTeams")
    public ResponseEntity<TeamResponse> getAllTeams(){
        logInfo("Inicia consulta de equipos: ");
        List<Equipo> teamList = new ArrayList<Equipo>();
        teamList = teamService.getAllTeams();
        return new ResponseEntity<TeamResponse>(new TeamResponse("Consulta exitosa", teamList), (teamList != null && !teamList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}