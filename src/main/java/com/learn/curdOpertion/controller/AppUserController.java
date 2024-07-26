package com.learn.curdOpertion.controller;

import com.learn.curdOpertion.entity.AppUser;
import com.learn.curdOpertion.repository.AppUserRespository;
import com.learn.curdOpertion.service.DirectoryFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.util.Arrays;

@Controller
public class AppUserController {
    @Autowired
   private DirectoryFinderService directoryFinderService;

    private AppUserRespository appUserRespository;

    @PostMapping(value = "/saveData", consumes = "multipart/form-data")
public @ResponseBody String saveData(String name,String email,String dob,String gender,String country, MultipartFile imageUpload ) {
        System.out.println(" name = "+name);
        System.out.println(" email = "+email);
       System.out.println(" dob = "+dob);
        System.out.println(" gender = "+gender);
        System.out.println(" country = "+country);
        System.out.println(" imageUpload = "+imageUpload);
        System.out.println("this saveData controller is called");

        String path = directoryFinderService.getProfilePhotoDir();
        System.out.println(" path = "+path);

        String fileName = imageUpload.getOriginalFilename();
        System.out.println(" fileName = "+fileName);

        int i = fileName.lastIndexOf('.');
        String extension = "";
        if (i >= 0) {
            extension = fileName.substring(i + 1);
        }
        System.out.println(" extension = "+extension);

        String sep = System.getProperty("file.separator");
        System.out.println(" sep = "+sep);

        byte[] bytes = null;
        try {
            bytes = imageUpload.getBytes();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("bytes = "+Arrays.toString(bytes));
        File dir = new File(path + sep + fileName);
       String imageType ="profilePhoto";
        String imgname = name + "_" + email + "_" + imageType + "." + extension;
        System.out.println("Date page stored value = "+imgname);
        // hrli.setLeaveImage(imgname);
       // hrrc.setAttachement(imgname);

        File serverFile = new File(path + sep + imgname);
        serverFile.renameTo(dir);
        BufferedOutputStream stream = null; // Initialize to null
        try {
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle other IO exceptions
            e.printStackTrace();
        } finally {
            // Make sure to close the stream in finally block to ensure it gets closed
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "done";
    }
}