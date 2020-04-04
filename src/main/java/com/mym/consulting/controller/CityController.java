package com.mym.consulting.controller;

import com.mym.consulting.entities.Ciudad;
import com.mym.consulting.model.response.CityResponse;
import com.mym.consulting.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CityController extends GenericController {

    @Autowired
    CityService cityService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getAllCities")
    public ResponseEntity<CityResponse> getAllCities(){
        logInfo("Inicia consulta de ciudades: ");
        List<Ciudad> cityList = new ArrayList<Ciudad>();
        cityList = cityService.getAllCities();
        return new ResponseEntity<CityResponse>(new CityResponse("Consulta exitosa", cityList), (cityList != null && !cityList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
