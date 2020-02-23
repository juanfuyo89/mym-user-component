package com.mym.consulting.services;

import com.mym.consulting.entities.Ciudad;
import com.mym.consulting.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<Ciudad> getAllCities(){
        List<Ciudad> allCities = cityRepository.findAll();
        return allCities;
    }

}
