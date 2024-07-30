package com.learn.curdOpertion.controller;

import com.learn.curdOpertion.service.DirectoryFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    @GetMapping("/getProfilePhoto/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        String pathRoot =  directoryFinderService.getProfilePhotoPath();
        System.out.println("pathRoot = "+pathRoot);
        Path rootLocation = Paths.get(pathRoot);

        System.out.println("path = "+rootLocation );
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ((UrlResource) resource).getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
