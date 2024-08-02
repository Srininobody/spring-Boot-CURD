package com.learn.curdOpertion.controller;

import com.learn.curdOpertion.entity.Menu;
import com.learn.curdOpertion.service.MenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.annotations.Parent;
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
    public @ResponseBody String updateMenu(String id,String menuName,String parantId,String status){
        System.out.println("Update function is called ....");
        String res ="F";
       Menu menu = menuService.getMenuById(id);
        if(menu !=null)
        {
            menu.setManuName(menuName);
            menu.setParent(parantId);
            menu.setStatus(status);
           Menu resultMenu = menuService.updateById(menu);
           if(resultMenu!=null)
           {
               res ="S";
           }
           else {
               res ="F";
           }
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
    @GetMapping("/getALlManuList")
    public @ResponseBody  String getAllMenuForTable(){
        String res ="F";
        List<Menu> datas = menuService.getAll();
        JSONArray jsonArray = new JSONArray();
        for(Menu menu : datas)
        {
            JSONObject jsonObject = new JSONObject();
            Long id = menu.getId();
            jsonObject.put("id" ,id);
            String menuName = menu.getManuName();
            jsonObject.put("menuName",menuName);
            String parentId = menu.getParent();
            if("0".equalsIgnoreCase(parentId))
            {
                jsonObject.put("parentId","0");
                jsonObject.put("parantName","No Parant");
            }
            else {
               Menu menu2 = menuService.getMenuById(parentId);
               String parentName = menu2.getManuName();
                jsonObject.put("parentId",parentId );
                jsonObject.put("parantName",parentName);

            }
           String status = menu.getStatus();
            if(status.equalsIgnoreCase("L"))
            {
                jsonObject.put("status", "Active");
            }
            else{
                jsonObject.put("status", "In-Active");
            }
            jsonArray.add(jsonObject);
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("manuList",jsonArray);
        System.out.println(">>>>>>>> = "+jsonObject1.toString());
        return jsonObject1.toString();
    }
}
