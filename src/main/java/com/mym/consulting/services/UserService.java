package com.mym.consulting.services;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.entities.Usuario;
import com.mym.consulting.model.User;
import com.mym.consulting.repositories.UserRepository;
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
            User userTemp = new User(user.getId(), user.getUserName(), user.getNombre(), role);
            response.add(userTemp);
        });
        return response;
    }

}
