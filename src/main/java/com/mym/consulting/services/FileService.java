package com.mym.consulting.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("C:\\Users\\hansa\\Documents\\documentosMyM");
    public void saveFile(MultipartFile file){
        String message;
        try {
            try {
                Files.copy(file.getInputStream(), this.rootLocation.resolve("archivo.pdf"));
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());

            message = "Successfully uploaded!";
            //return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Failed to upload!";
            //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
