package com.mym.consulting.services;

import com.mym.consulting.entities.Usuario;
import com.mym.consulting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String validateUser(String user, String password){
        Usuario usuario = this.userRepository.findUserByUserAndPassword(user, password);
        String token = (usuario != null) ? usuario.getToken() : "";
        return token;
    }

}
