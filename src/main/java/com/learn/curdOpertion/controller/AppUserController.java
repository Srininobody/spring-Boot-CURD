package com.learn.curdOpertion.controller;

import com.learn.curdOpertion.entity.AppUser;
import com.learn.curdOpertion.excelDownload.UserExcel;
import com.learn.curdOpertion.repository.AppUserRespository;
import com.learn.curdOpertion.service.AppUserService;
import com.learn.curdOpertion.service.DirectoryFinderService;
import com.learn.curdOpertion.serviceImpl.AppUserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class AppUserController {
    @Autowired
    private DirectoryFinderService directoryFinderService;
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserExcel userExcel;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @PostMapping(value = "/saveData", consumes = "multipart/form-data")
    public @ResponseBody String saveData(String name,String email,String dob,String gender,String country, MultipartFile imageUpload ) throws ParseException {
        System.out.println(" name = "+name);
        System.out.println(" email = "+email);
        System.out.println(" dob = "+dob);
        System.out.println(" gender = "+gender);
        System.out.println(" country = "+country);
        System.out.println(" imageUpload = "+imageUpload);
        System.out.println("this saveData controller is called");

    AppUser user = new AppUser();
    user.setName(name);
    user.setEmail(email);
    Date dates = format.parse(dob);
    user.setDob(dates);

    user.setGender(gender);
    user.setCountry(country);

    if(imageUpload != null)
    {
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
        //System.out.println("bytes = "+Arrays.toString(bytes));
        File dir = new File(path + sep + fileName);
        String imageType ="profilePhoto";
        String imgname = name + "_" + email + "_" + imageType + "." + extension;
        System.out.println("Date page stored value = "+imgname);
        user.setProfilePic(imgname);

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
    }
    String res = "fail";
       AppUser appUser =  appUserService.saveUser(user);
        if(appUser !=null)
        {
            res ="done";
        }

        return res;
    }
    @GetMapping("/getAllUser")
    public @ResponseBody String getAllUser(){

        JSONArray jsonArray = new JSONArray();
        List<AppUser> userList = appUserService.getAllUser();
        System.out.println("output Size = "+userList.size());
        for(AppUser user : userList){
            JSONObject userJson = new JSONObject();
            userJson.put("name" ,user.getName());

            userJson.put("email",user.getEmail());
            Date dd = user.getDob();
            String date = format.format(dd);
            System.out.println("Dob = "+date);
            userJson.put("dob" , date);
            userJson.put("gender",user.getGender());
            userJson.put("country", user.getCountry());
            userJson.put("photo",user.getProfilePic());
            jsonArray.add(userJson);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users", jsonArray);
        return jsonObject.toString();
    }
    @GetMapping("/downloadUserDetailsExcel")
    public ResponseEntity<InputStreamResource> downloadUserDetailsExcel() throws IOException {
        return  userExcel.userDetailsUpdateExcel();
    }

}