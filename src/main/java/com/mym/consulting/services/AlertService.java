package com.mym.consulting.services;

import com.mym.consulting.entities.Alerta;
import com.mym.consulting.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    AlertRepository alertRepository;
    public List<Alerta> getAllAlerts(){
        return alertRepository.findAll();
    }
    public void generateAlert(Alerta alert){
        alertRepository.save(alert);
    }
    public void markAlertAsRead(Integer alertId){
        Alerta alert = alertRepository.findById(alertId).get();
        alert.setLeida("S");
        alertRepository.save(alert);
    }
}
