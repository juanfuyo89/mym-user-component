package com.mym.consulting.services;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.Usuario;
import com.mym.consulting.entities.UsuariosEquipo;
import com.mym.consulting.model.User;
import com.mym.consulting.repositories.UserRepository;
import com.mym.consulting.repositories.UsersTeamRepository;
import com.mym.consulting.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    UsersTeamRepository usersTeamRepository;

    public String validateUser(String user, String password){
        String token = "";
        Usuario usuario = this.userRepository.findUserByUserAndPassword(user, password);
        if (usuario != null && !usuario.getToken().isEmpty()) {
            usuario.setToken(tokenGenerator.getJWTToken(usuario.getUserName()));
            this.userRepository.save(usuario);
            token = usuario.getToken();
        }
        return token;
    }

    public List<User> getAllUsers(){
        List<Usuario> allUsers = userRepository.findAll();
        List<User> response = new ArrayList<>();
        allUsers.forEach(user -> {
            String role = (user.getIdRol() == 1) ? "ADMINISTRADOR" : "USUARIO";
            User userTemp = new User(user.getId(), user.getUserName(), user.getNombre(), role, false);
            response.add(userTemp);
        });
        return response;
    }

    public List<User> getUsersByTeam(Integer teamId){
        List<UsuariosEquipo> allUsers = usersTeamRepository.findByTeam(teamId);
        List<User> response = new ArrayList<>();
        allUsers.forEach(user -> {
            Usuario userBd = userRepository.findById(user.getId().getIdUsuario()).get();
            String role = (userBd.getIdRol() == 1) ? "ADMINISTRADOR" : "USUARIO";
            User userTemp = new User(userBd.getId(), userBd.getUserName(), userBd.getNombre(), role, user.getEsLider());
            response.add(userTemp);
        });
        return response;
    }

}
