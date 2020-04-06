package com.mym.consulting.services;

import com.mym.consulting.entities.Alerta;
import com.mym.consulting.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    @Autowired
    AlertRepository alertRepository;
    public void generateAlert(Alerta alert){
        try {
            alertRepository.save(alert);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
