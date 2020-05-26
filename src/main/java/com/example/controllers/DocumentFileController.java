package com.example.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.example.Utils;
import com.example.model.DocumentFile;
import com.example.model.view.ViewDocumentFile;
import com.example.service.DocumentFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@RestController
public class DocumentFileController {

    @Autowired
    DocumentFileService dfs;

    @PostMapping("/document_file")
    public ViewDocumentFile uploadDocumentFile(MultipartFile file, String fileName, int ort, String base64, int projectId, int moduleId, int userId, String descFile){
    	ViewDocumentFile vdf = null;

    	File folderUpload = new File("../upload");
        if(!folderUpload.exists()) folderUpload.mkdir();

        File folderUser = new File(folderUpload+"/usr_"+userId);
        if(!folderUser.exists()) folderUser.mkdir();

        File folderModule = new File(folderUser+"/mdl_"+moduleId);
        if(!folderModule.exists()) folderModule.mkdir();
        String pathStr = "";
        Long fileSize = new Long(0);

        try{
            if(!base64.equals("")){
                String[] sourceData = base64.split(",");
                BufferedImage bufferedImage = null;
                byte[] imageByte;

//                BASE64Decoder decoder = new BASE64Decoder();
                try {
                    imageByte = Base64.getDecoder().decode(sourceData[1]);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageByte);
                    bufferedImage = ImageIO.read(byteArrayInputStream);
                    byteArrayInputStream.close();

                    Utils utils = new Utils();
                    BufferedImage rotatedImage = bufferedImage;
                    if(ort != 0 && ort != 1) rotatedImage = utils.rotateImage(ort, bufferedImage);

                    File file1 = new File("../upload/usr_"+userId+"/mdl_"+moduleId+"/"+fileName);
                    ImageIO.write(rotatedImage, "jpg", file1);
                    pathStr = "../upload/usr_"+userId+"/mdl_"+moduleId+"/"+fileName;
                    fileSize = file1.length();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                fileSize = file.getSize();
                byte[] bytes = file.getBytes();
                Path path = Paths.get("../upload/usr_"+userId+"/mdl_"+moduleId+"/"+file.getOriginalFilename());
                Files.write(path, bytes);
                pathStr = path.toString();
            }
            // insert data
            int idx = fileName.lastIndexOf(".");
            String extension = fileName.substring(idx, fileName.length());
            System.out.println(descFile);
            vdf = dfs.upload(projectId, moduleId, userId, fileSize, extension, fileName, descFile, pathStr);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return vdf;
    }
    
    @PostMapping("/document_file_list")
    public List<ViewDocumentFile> getViewDocumentFile(int projectId, int userId, String sessionId){
    	Utils utils = new Utils();
    	Boolean isAccess = utils.getAccess(userId, sessionId);
    	List<ViewDocumentFile> vdfl = new ArrayList<ViewDocumentFile>();
    	if(isAccess) {
    		vdfl = dfs.getViewDocumentFileByProjectId(projectId, userId);
    	}
    	return vdfl;
    }
    
    @GetMapping("/document_file_download")
    public File getDocumentFile() {
    	File file2Upload = new File("F:\\spring\\upload\\capture001.png");
    	return file2Upload;
    }

    @PostMapping("/list_document_file")
    public List<DocumentFile> listDocFile(@RequestParam int moduleId){
        return dfs.getDataListDocFile(moduleId);
    }

    @PostMapping("/delete_document_file")
    public int delete(@RequestParam int moduleId, int projectId, String fileName, int userId){
        int del = dfs.delete(moduleId, projectId, fileName, userId);
        return del;
    }
}