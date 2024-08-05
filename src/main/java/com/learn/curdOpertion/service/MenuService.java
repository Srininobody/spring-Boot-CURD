package com.learn.curdOpertion.service;

import com.learn.curdOpertion.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    public Menu saveMenu(Menu menu);
    public Menu updateById(Menu menu);
    public Menu getMenuById(String id);
    public List<Menu> getAll();

    public List<Object []> getAllParent();
    public List<Menu> getAllActiveMenus();
}
