package com.learn.curdOpertion.serviceImpl;

import com.learn.curdOpertion.entity.Menu;
import com.learn.curdOpertion.repository.MenuRepositroy;
import com.learn.curdOpertion.service.MenuService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuserviceImpl implements MenuService {
    @Autowired
    private MenuRepositroy menuRepositroy;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Menu saveMenu(Menu menu) {
        return menuRepositroy.save(menu);
    }

    @Override
    public Menu updateById(Menu menu) {
        return menuRepositroy.save(menu);
    }

    @Override
    public Menu getMenuById(String ids) {
        Long id = Long.parseLong(ids);
       Optional<Menu> getdate = menuRepositroy.findById(id);
       if(getdate.isPresent())
       {
           return getdate.get();
       }
        return null;

    }


    @Override
    public List<Menu> getAll() {
        return menuRepositroy.findAll();
    }

    @Transactional
    public List<Object[]> getAllParent() {
        String query ="select id,manu_name from menu";
        System.out.println(">>>>>>>>>>>>>>> Qury = "+query);
        return  entityManager.createNativeQuery(query).getResultList();
    }

    @Override
    public List<Menu> getAllActiveMenus() {
        return menuRepositroy.findAllActiveMenus();
    }
}
