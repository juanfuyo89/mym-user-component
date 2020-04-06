package com.mym.consulting.controller;

import com.mym.consulting.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController extends GenericController{
    @Autowired
    FileService fileService;

    @PostMapping("/saveFile")
    public void saveFile(){
        logInfo("Cargando archivo");
        //fileService.saveFile(file);
    }
}
