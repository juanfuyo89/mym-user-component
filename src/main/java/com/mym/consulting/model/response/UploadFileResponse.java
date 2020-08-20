package com.mym.consulting.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse extends Response {
    private Integer fileId;
    public UploadFileResponse(String message, Integer fileId) {
        super.setResponseMessage(message);
        this.fileId = fileId;
    }
}
