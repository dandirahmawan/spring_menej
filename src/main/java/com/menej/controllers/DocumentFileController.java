package com.menej.controllers;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.menej.FileStore;
import com.menej.Utils;
import com.menej.model.db.DocumentFile;
import com.menej.model.view.ViewDocumentFile;
import com.menej.service.DocumentFileService;

import com.sun.org.apache.xpath.internal.operations.Mult;
import lombok.extern.slf4j.Slf4j;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

@RestController
@Slf4j
public class DocumentFileController {

    @Autowired
    DocumentFileService dfs;

    @Autowired
    ServletContext context;

    @Value("${file.upload-dir}")
    String directory;

    @PostMapping("/document_file")
    public ViewDocumentFile uploadDocumentFile(MultipartFile file,
                                               String fileName,
                                               int ort,
                                               String base64,
                                               int projectId,
                                               int moduleId,
                                               int userId,
                                               String descFile){
    	ViewDocumentFile vdf = null;
        File fileUploaded = null;
        FileStore fs = new FileStore();

        /*create folder moduel/user*/
        String uplodaDir = fs.folderUserModule(userId, moduleId);

        /*uploading document file*/
        if(!base64.equals("")){
            fileUploaded = fs.storeFileBase64(uplodaDir, base64, ort, fileName);
        }else{
            fileUploaded = fs.storeFile(file, uplodaDir);
        }

        int idx = fileName.lastIndexOf(".");
        String pathStr = fileUploaded.getAbsolutePath();
        Path path = Paths.get(pathStr);

        Long fileSize = new Long(0);
        try{
            fileSize = Files.size(path);
        }catch (Exception e){}

        String extension = fileName.substring(idx, fileName.length());
        System.out.println(descFile);
        vdf = dfs.upload(projectId, moduleId, userId, fileSize, extension, fileName, descFile, pathStr);
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

    @PostMapping("/xml_convert")
    public StringBuilder xmlConvert(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        String fileNamePath = file.getOriginalFilename().replace("[", "");
        fileNamePath = fileNamePath.replace("]", "");

        File folderUpload = new File("../upload/xml");
        if(!folderUpload.exists()) folderUpload.mkdir();
        Path path = Paths.get("../upload/xml/"+fileNamePath);

        try{
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }

        File file1 = new File(path.toString());
        StringBuffer sb = new StringBuffer();
        StringBuilder sbd = new StringBuilder();
        String textData;
        try{
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);

            int i = 0;
            while ((textData = br.readLine()) != null) {
                String data = textData.replace("\n\t", "");
                int idx = data.indexOf("<");
//                if(data.substring(idx, idx + 11).equals("<xs:element")){
                if(data.matches(".*element.*")){
                    System.out.println(data);
                    int idx1 = data.indexOf("name=") + 6;
                    String text = data.substring(idx1 + 6, data.length());
                    int idx2 = text.indexOf(" ");
                    String namaElement = data.substring(idx1, idx1 + idx2 + 5);
                    if(idx1 >= 6){
                        i++;
                        String ot = i+". "+namaElement;
                        sbd.append(ot+"\n");
                    }
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return sbd;
    }
}