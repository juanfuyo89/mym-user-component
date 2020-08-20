package com.mym.consulting.controller;

import com.mym.consulting.entities.Archivo;
import com.mym.consulting.entities.EtapasProyecto;
import com.mym.consulting.model.response.FileResponse;
import com.mym.consulting.model.response.Response;
import com.mym.consulting.model.response.StagesByProjectResponse;
import com.mym.consulting.model.response.UploadFileResponse;
import com.mym.consulting.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class FileController extends GenericController{

    @Autowired
    FileService fileService;

    @RequestMapping(value="/saveFile",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UploadFileResponse> saveFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        logInfo("Cargando archivo");
        Integer fileId = fileService.saveFile(file);
        return new ResponseEntity<UploadFileResponse>(new UploadFileResponse("Archivo guardado", fileId),
                (fileId > 0) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET,
            path = "/getFileByDeliverable/{projectId}/{stageId}/{deliverableId}")
    public ResponseEntity<FileResponse> getFileByDeliverable(@PathVariable(required = true) Integer projectId,
                                                             @PathVariable(required = true) Integer stageId,
                                                             @PathVariable(required = true) Integer deliverableId){
        Archivo file = fileService.getFileByDeliverable(projectId, stageId, deliverableId);
        return new ResponseEntity<FileResponse>(new FileResponse(
                (file != null) ? "Consulta exitosa" : "No se encontró un archivo asociado a ese entregable", file),
                HttpStatus.OK);
    }

}
