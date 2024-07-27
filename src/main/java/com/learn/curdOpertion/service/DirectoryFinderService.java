package com.learn.curdOpertion.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DirectoryFinderService {

    @Value("${project.name}")
    private String projectName;

    public String getProfilePhotoDir() {
        String osName = System.getProperty("os.name").toLowerCase();
        String retStr = "";
        System.out.println("projectName = "+projectName);
        System.out.println("Os Name = "+osName);
        if (osName.contains("win")) {
            // Check drives one by one
            String[] drives = {"D:", "E:", "F:","G:","C:"};
            for (String drive : drives) {
                System.out.println(" drive = "+drive);
                File directory = new File(drive + "\\");
                if (directory.isDirectory()) {
                    System.out.println("location = "+drive +"is present  -> "+directory);
                        File directory1 = new File(drive + "\\" + projectName);
                    if (directory1.isDirectory()) {
                        File directory2 = new File(drive + "\\" + projectName + "\\profilePhoto");
                        if (directory2.isDirectory()) {
                            retStr = drive + "\\" + projectName + "\\profilePhoto";
                            break;
                        }
                        else{
                            File profilePhotoDir = new File(drive + "\\" + projectName + "\\profilePhoto");
                            if (!profilePhotoDir.exists()) {
                                System.out.println("calling make directory..... ");
                                profilePhotoDir.mkdirs(); // Create the directory if it does not exist
                                retStr = drive + "\\" + projectName + "\\profilePhoto";
                                break;

                            }
                        }
                    }

                }

                File directory22 = new File(drive + "\\" + projectName + "\\profilePhoto");
                if (directory22.isDirectory()) {
                    retStr = drive + "\\" + projectName + "\\profilePhoto";
                    break;
                }
            }
        }
        else {

            retStr = "/root/" + projectName + "/profilePhoto";
        }
        System.out.println("retStr = "+retStr);
        return retStr;
    }
    public String getProfilePhotoPath(){
        String osName = System.getProperty("os.name").toLowerCase();
        String retStr = "";
        if (osName.contains("win")) {
            String[] drives = {"D:", "E:", "F:","G:","C:"};

            for (String drive : drives) {
                System.out.println(" drive = "+drive);
                File directory22 = new File(drive + "\\" + projectName + "\\profilePhoto");
                if (directory22.isDirectory()) {
                    retStr = drive + "\\" + projectName + "\\profilePhoto";
                    break;
                }
            }
        }
        else{
            retStr = "/root/" + projectName + "/profilePhoto";
        }

     return retStr;
    }
}
