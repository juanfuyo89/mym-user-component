package com.mym.consulting.model.dto;

import com.mym.consulting.entities.Contrato;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private Integer id;
    private Contrato contrato;
    private String codigoProyecto;
    private String estado;
    private Integer idCliente;
    private Integer idCiudad;
    private Integer idEquipo;
    private Integer idFormaPago;
    private LocalDate inicioEjecucion;
    private LocalDate finEjecucion;
    private LocalDate inicioProrroga;
    private LocalDate finProrroga;
    private LocalDate inicioSuspension;
    private LocalDate finSuspension;
    private String status;
}
