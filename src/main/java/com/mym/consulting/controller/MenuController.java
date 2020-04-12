package com.mym.consulting.controller;

import com.mym.consulting.entities.Menu;
import com.mym.consulting.entities.MenuRol;
import com.mym.consulting.model.response.MenuResponse;
import com.mym.consulting.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController extends GenericController {

    @Autowired
    MenuService menuService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET, path = "/getMenuByRole/{idRole}")
    public ResponseEntity<MenuResponse> getMenuByRole(@PathVariable("idRole") Integer idRole){
        logInfo("Inicia consulta de Menu por rol de usuario: ");
        List<Menu> menuList = menuService.getMenusByRol(idRole);
        return new ResponseEntity<MenuResponse>(new MenuResponse("Consulta exitosa", menuList),
                (menuList != null && !menuList.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
