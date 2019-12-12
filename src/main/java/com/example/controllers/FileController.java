package com.example.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
    @GetMapping("/email_message")
    public String emailMessage(){
        return "NewFile";
    }
    
    @RequestMapping(value = "/images/{imageId}")
    public void getImage(HttpServletResponse response, @PathVariable String imageId) throws IOException{
    	File f = new ClassPathResource("static/images/"+imageId).getFile();
    	Path path = f.toPath();
    	String mimeType = Files.probeContentType(path);
    	response.setContentType(mimeType);
    	
    	InputStream in = null;
    	if(f.exists()) {
    		in = new FileInputStream(f);
    	}
    	
    	IOUtils.copy(in, response.getOutputStream());
    }
    
    
    @RequestMapping(value = "/file/{fileId}")
    public void photo(HttpServletResponse response, @PathVariable String fileId) throws Exception {
    	File f = new File("../upload/"+fileId);
    	Path path = f.toPath();
    	String mimeType = Files.probeContentType(path);
    	System.out.println("mime type is = "+mimeType);
    	InputStream in = null;
    	
    	if(f.exists()) {
    		in = new FileInputStream(f);
    	}
    	
    	response.setContentType(mimeType);
        IOUtils.copy(in, response.getOutputStream());
    }
    
}