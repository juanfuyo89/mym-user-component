package com.mym.consulting.controller;

import com.mym.consulting.entities.Usuario;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.model.dto.User;
import com.mym.consulting.model.response.RoleResponse;
import com.mym.consulting.model.response.UserResponse;
import com.mym.consulting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends GenericController {

    @Autowired
    UserService userService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/userlogin/{user}/{password}")
    public ResponseEntity<RoleResponse> loginUser(@PathVariable("user") String user, @PathVariable("password") String password){
        Usuario userResponse = userService.validateUser(user, password);
        logInfo("Validando usuario: " + user);
        return new ResponseEntity<RoleResponse>(new RoleResponse("Consulta exitosa", userResponse), HttpStatus.OK);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllUsers")
    public ResponseEntity<UserResponse> getAllUsers(){
        logInfo("Inicia consulta de usuarios: ");
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<UserResponse>(new UserResponse("Consulta exitosa", userList),
                (userList != null && !userList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getUsersByTeam/{teamId}")
    public ResponseEntity<UserResponse> getUsersByTeam(@PathVariable("teamId") Integer teamId){
        logInfo("Inicia consulta de usuarios: ");
        List<User> userList = userService.getUsersByTeam(teamId);
        return new ResponseEntity<UserResponse>(new UserResponse("Consulta exitosa", userList),
                (userList != null && !userList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/testToken")
    public ResponseEntity<String> tokenTest(){
        return new ResponseEntity<String>("token valido", HttpStatus.OK);
    }

}
