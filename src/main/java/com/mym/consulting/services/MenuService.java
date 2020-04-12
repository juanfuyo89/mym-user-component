package com.mym.consulting.services;

import com.mym.consulting.entities.Menu;
import com.mym.consulting.entities.MenuRol;
import com.mym.consulting.repositories.MenuRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRolRepository menuRolRepository;

    public List<Menu> getMenusByRol(Integer idRole) {
        List<Menu> menuList = new ArrayList<>();
        List<MenuRol> menuRolList = this.menuRolRepository.findByIdRole(idRole);
        if (menuRolList != null && !menuRolList.isEmpty())
            menuRolList.forEach(menuRol -> {
                menuList.add(menuRol.getId().getMenu());
            });
        return menuList;
    }

}
