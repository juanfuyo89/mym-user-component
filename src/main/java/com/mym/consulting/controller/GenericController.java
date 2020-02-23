package com.mym.consulting.controller;


import com.mym.consulting.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GenericController {

    protected final static Logger LOGGER = LogManager.getRootLogger();



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> generalExceptionHandler(Exception e) {
        logError(e);
        return new ResponseEntity<Response>(Response.getIntance(Response.GENERAL_ERR_MSG),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected void logInfo(String message) {
        LOGGER.info(message);
    }

    protected void logError(Exception e) {
        LOGGER.error("Error en la solicitud: " + e.getMessage());
        LOGGER.catching(e);
    }
}
