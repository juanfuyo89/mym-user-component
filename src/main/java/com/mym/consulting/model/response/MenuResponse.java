package com.mym.consulting.model.response;

import com.mym.consulting.entities.Entregable;
import com.mym.consulting.entities.Menu;
import com.mym.consulting.entities.MenuRol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse extends Response{
    public MenuResponse(String message, List<Menu> menuList){
        super.setResponseMessage(message);
        this.menuList = menuList;
    }

    private List<Menu> menuList;
}
