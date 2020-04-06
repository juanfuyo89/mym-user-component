package com.mym.consulting.services;

import com.mym.consulting.entities.*;
import com.mym.consulting.model.request.SaveTeamRequest;
import com.mym.consulting.repositories.TeamRepository;
import com.mym.consulting.repositories.UsersTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UsersTeamRepository usersTeamRepository;

    @Transactional
    public void saveTeam(SaveTeamRequest request) {
        Equipo team = request.getTeam();
        this.teamRepository.save(team);
        request.getUserTeamList().forEach(user -> {
            UsuariosEquipo usersTeam = new UsuariosEquipo();
            user.setTeamId(team.getId());
            UsuariosEquipoPK usuariosEquipoPK = new UsuariosEquipoPK();
            usuariosEquipoPK.setIdEquipo(user.getTeamId());
            usuariosEquipoPK.setIdUsuario(user.getUserId());
            usersTeam.setId(usuariosEquipoPK);
            usersTeam.setEsLider(user.getIsLeader());
            this.usersTeamRepository.save(usersTeam);
        });
    }

    public List<Equipo> getAllTeams(){
        List<Equipo> allTeams = teamRepository.findAll();
        return allTeams;
    }

}
