package com.contentflow.contentflow.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException {
        //extract fileName
        String name = file.getOriginalFilename();

        //generate unique randomName
        String randomId = UUID.randomUUID().toString();
        String uniqueName = randomId.concat(name.substring(name.lastIndexOf("."))); //if name=="name.png" this method adds .png to randomId at end


        //create full path
        String filePath = path+File.separator+uniqueName;

        //create blogImages folder if not created
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        //copy file
        Files.copy(file.getInputStream(), Paths.get(filePath)); //throws IOException if error occurs
        return uniqueName;
    }

    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
