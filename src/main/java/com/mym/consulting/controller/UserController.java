package com.mym.consulting.controller;

import com.mym.consulting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/userlogin/{user}/{password}")
    public String loginUser(@RequestParam("user") String user, @RequestParam("password") String password){
        return userService.validateUser(user, password);
    }
}
