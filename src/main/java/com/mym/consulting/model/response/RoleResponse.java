package com.mym.consulting.model.response;

import com.mym.consulting.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse extends Response {
    public RoleResponse(String message, Usuario user){
        super.setResponseMessage(message);
        this.user = user;
    }
    private Usuario user;
}
