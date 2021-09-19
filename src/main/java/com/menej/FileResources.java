package com.menej;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileResources {

    @Value("${file.upload-dir}")
    String directory;

    public Boolean storeFile (){
        File dir = new File(directory);
        if(!dir.exists()) {

        }

        return true;
    }

}
