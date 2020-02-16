package com.mym.consulting.controller;

import com.mym.consulting.model.Response;
import com.mym.consulting.services.UserService;
import com.mym.consulting.util.TokenGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final static Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    UserService userService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/userlogin/{user}/{password}")
    public ResponseEntity<String> loginUser(@PathVariable("user") String user, @PathVariable("password") String password){
        String token = userService.validateUser(user, password);
        logInfo("Validando usuario: " + user);
        return new ResponseEntity<String>(token, (token != null && !token.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/testToken")
    public ResponseEntity<String> tokenTest(){
        return new ResponseEntity<String>("token valido", HttpStatus.OK);
    }

    /**
     * Maneja las excepciones generales de la Api
     * @param e Exception
     * @return ResponseEntity<Response>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> generalExceptionHandler(Exception e) {
        logError(e);
        return new ResponseEntity<Response>(Response.getIntance(Response.GENERAL_ERR_MSG),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logInfo(String message) {
        LOGGER.info(message);
    }

    private void logError(Exception e) {
        LOGGER.error("Error en la solicitud: " + e.getMessage());
        LOGGER.catching(e);
    }

}
