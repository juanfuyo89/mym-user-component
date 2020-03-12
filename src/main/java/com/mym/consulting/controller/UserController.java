package com.mym.consulting.controller;

import com.mym.consulting.entities.Etapa;
import com.mym.consulting.model.Response;
import com.mym.consulting.model.StageResponse;
import com.mym.consulting.model.User;
import com.mym.consulting.model.UserResponse;
import com.mym.consulting.services.UserService;
import com.mym.consulting.util.TokenGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController extends GenericController{

    @Autowired
    UserService userService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/userlogin/{user}/{password}")
    public ResponseEntity<Response> loginUser(@PathVariable("user") String user, @PathVariable("password") String password){
        String token = userService.validateUser(user, password);
        logInfo("Validando usuario: " + user);
        return new ResponseEntity<Response>(Response.getIntance(token), (token != null && !token.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllUsers")
    public ResponseEntity<UserResponse> getAllUsers(){
        logInfo("Inicia consulta de usuarios: ");
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<UserResponse>(new UserResponse("Consulta exitosa", userList),
                (userList != null && !userList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/testToken")
    public ResponseEntity<String> tokenTest(){
        return new ResponseEntity<String>("token valido", HttpStatus.OK);
    }

}
