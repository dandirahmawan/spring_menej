package com.example.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.Spring;

import com.example.model.DocumentFile;
import com.example.service.DocumentFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentFileController {

    @Autowired
    DocumentFileService dfs;

    @PostMapping("/document_file")
    public DocumentFile uplaodDocumentFile(@RequestParam("file") MultipartFile file, int projectId, int moduleId, int userId){
        DocumentFile df = null;

        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get("../upload/"+file.getOriginalFilename());
            Files.write(path, bytes);
            
            // insert data
            String fileName = file.getOriginalFilename();
            Long fileSize = file.getSize();
            int idx = fileName.lastIndexOf(".");
            String extension = fileName.substring(idx, fileName.length());
            df = dfs.upload(projectId, moduleId, userId, fileSize, extension, fileName);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return df;
    }
    
    @GetMapping("/document_file_download")
    public File getDocumentFile() {
    	File file2Upload = new File("F:\\spring\\upload\\capture001.png");
    	return file2Upload;
//    	InputStreamResource resource = null;
//    	try {
//    		resource = new InputStreamResource(new FileInputStream(file2Upload));
//    		System.out.println(resource);
//    	}catch(Exception e) {
//    		e.printStackTrace();
//    	}
////    	File files = file.getAbsoluteFile();
//    	
//    	HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        
//    	return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file2Upload.length())
//                .contentType(MediaType.parseMediaType())
//                .body(resource);
    }

    @PostMapping("/list_document_file")
    public List<DocumentFile> listDocFile(@RequestParam int moduleId){
        return dfs.getDataListDocFile(moduleId);
    }

    @PostMapping("/delete_document_file")
    public int delete(@RequestParam int moduleId, int projectId, String fileName, int userId){
        int del = dfs.delete(moduleId, projectId, fileName);
        return del;
    }
}