package com.learn.curdOpertion.controller;

import com.learn.curdOpertion.entity.Menu;
import com.learn.curdOpertion.service.MenuService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {

    @Autowired
   private MenuService menuService;

    @PostMapping("/saveMenuCreate")
    public @ResponseBody String saveManu( String menuName,  String parentID){
        String res ="F";
        System.out.println("menuName = "+menuName);
        System.out.println("parent id = "+parentID);
        Menu menu = new Menu();
        menu.setManuName(menuName);
        menu.setParent(parentID);
        menu.setStatus("L");
       Menu Result = menuService.saveMenu(menu);
       if(Result !=null){
           res ="S";
       }

        return res;
    }
    @PutMapping("/updateMenu")
    public String updateMenu(String id,String menuName,String parantId,String status){
        String res ="F";
       Menu menu = menuService.getMenuById(id);
        if(menu !=null)
        {
            menu.setManuName(menuName);
            menu.setParent(parantId);
            menu.setStatus(status);
            menuService.updateById(menu);
        }
        return res;
    }
    @GetMapping("/getAllParent")
    public  @ResponseBody String getParentId(){
        System.out.println("this dropDown is called...");
       List<Object [] > datas = menuService.getAllParent();
        System.out.println(datas.size());
        List<JSONObject> jsonObject = new ArrayList<>();
        for(Object [] arr :datas) {
            JSONObject responseObj = new JSONObject();
            Long id = (Long) arr[0];
            String name =(String) arr[1];

            System.out.println("------------> trip code = "+id);
            System.out.println("------------> trip name = "+name);
            responseObj.put("code", id);
            responseObj.put("name", name);
            jsonObject.add(responseObj);

        }
        return jsonObject.toString();
    }
}
