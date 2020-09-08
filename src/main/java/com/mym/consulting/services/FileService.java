package com.mym.consulting.services;

import com.mym.consulting.entities.Archivo;
import com.mym.consulting.entities.EntregablesEtapa;
import com.mym.consulting.repositories.DeliverableStagesRepository;
import com.mym.consulting.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final Path rootLocation = Paths.get("/opt/tomcat/webapps/ROOT/temp/");

    @Autowired
    FileRepository fileRepository;
    @Autowired
    DeliverableStagesRepository deliverableStagesRepository;

    public Integer saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename().replace(" ", "_");
        Path path = this.rootLocation.resolve(fileName);
        if(Files.exists(path)){
            Files.delete(path);
        }
        Files.copy(file.getInputStream(), path);
        Archivo archivo = new Archivo();
        archivo.setName(fileName);
        archivo.setPath("http://18.188.39.174:8080/temp/" + fileName);
        archivo.setTimestampCarga(new Timestamp(System.currentTimeMillis()));
        fileRepository.save(archivo);
        return archivo.getId();
    }

    public Archivo getFileByDeliverable(Integer projectId, Integer stageId, Integer deliverableId) {
        EntregablesEtapa entregablesEtapa = deliverableStagesRepository.findById(projectId, stageId, deliverableId);
        Archivo archivo = null;
        if(entregablesEtapa != null && entregablesEtapa.getIdArchivo() != null  && entregablesEtapa.getIdArchivo() > 0) {
            Optional<Archivo> optional = fileRepository.findById(entregablesEtapa.getIdArchivo());
            archivo = (optional.isPresent()) ? optional.get() : null;
        }
        return archivo;
    }
}
