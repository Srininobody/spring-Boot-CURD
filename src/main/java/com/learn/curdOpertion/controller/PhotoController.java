package com.learn.curdOpertion.controller;

import com.learn.curdOpertion.service.DirectoryFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotoController {
    @Autowired
    DirectoryFinderService directoryFinderService;

    @GetMapping("/getProfilePhoto")
   public String getProfilePhoto(String photoName){
      String path =  directoryFinderService.getProfilePhotoPath();
      System.out.println("path = "+path );



    return "";

    }
}
