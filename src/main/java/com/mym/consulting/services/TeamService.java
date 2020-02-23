package com.mym.consulting.services;

import com.mym.consulting.entities.Equipo;
import com.mym.consulting.entities.Etapa;
import com.mym.consulting.repositories.StageRepository;
import com.mym.consulting.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Equipo> getAllTeams(){
        List<Equipo> allTeams = teamRepository.findAll();
        return allTeams;
    }

}
