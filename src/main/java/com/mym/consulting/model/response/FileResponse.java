package com.mym.consulting.model.response;

import com.mym.consulting.entities.Archivo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse extends Response {
    private Archivo file;
    public FileResponse(String message, Archivo file) {
        super.setResponseMessage(message);
        this.file = file;
    }
}
