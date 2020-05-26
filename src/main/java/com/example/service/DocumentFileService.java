package com.example.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.example.model.DocumentFile;
import com.example.model.view.ViewDocumentFile;
import com.example.repo.DocumentFileRepo;
import com.example.repo.ViewDocumentFileRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentFileService{
    @Autowired
    DocumentFileRepo dfr;
    
    @Autowired
    ViewDocumentFileRepo vdfr;

    public ViewDocumentFile upload(int projectId, int moduleId, int userId, Long size, String extension, String fileName, String descFile, String path){
        Date date = new Date();
        ViewDocumentFile vdf = null;
        DocumentFile df = new DocumentFile();
        df.setProjectId(projectId);
        df.setModulId(moduleId);
        df.setUserId(userId);
        df.setFileSize(Integer.parseInt(size.toString()));
        df.setExtension(extension);
        df.setUploadDate(date);
        df.setFileName(fileName);
        df.setDescription(descFile);
        df.setPath(path);
        dfr.save(df);
        
        vdf = vdfr.findByModulIdAndProjectIdAndUserIdAndUploadDate(moduleId, projectId, userId, date);
        
        return vdf;
    }

    public List<DocumentFile> getDataListDocFile(int moduleId){
        return dfr.findByModulId(moduleId);
    }

    public int delete(int modulId, int projectId, String fileName, int userId){
    	List<DocumentFile> documentFiles = dfr.findByModulIdAndProjectIdAndFileName(modulId, projectId, fileName);
    	
    	File folderUpload = new File("../upload");
        if(!folderUpload.exists()) folderUpload.mkdir();
        
        File folderDeleted = new File(folderUpload+"/deleted");
        if(!folderDeleted.exists()) folderDeleted.mkdir();
        
        File folderDeletedUser = new File(folderDeleted+"/usr_"+userId);
        if(!folderDeletedUser.exists()) folderDeletedUser.mkdir();
        
        File folderDeletedModuleId = new File(folderDeletedUser+"/mdl_"+modulId);
        if(!folderDeletedModuleId.exists()) folderDeletedModuleId.mkdir();
        
    	for(int i = 0; i < documentFiles.size(); i++) {
    		try {
				Path temp = Files.move(Paths.get(documentFiles.get(i).getPath()), Paths.get(folderDeletedModuleId+"/"+fileName));
				if(temp != null) {
					System.out.println("file removed");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
    	
        int del = dfr.deleteDocumentFile(modulId, projectId, fileName);
        return del;
    }
    
    public List<ViewDocumentFile> getDocByUser(int userId){
        return vdfr.findByUserId(userId, String.valueOf(userId));
    }
    
    public List<ViewDocumentFile> getDocByUserDetail(int userId, int userLogin){
        return vdfr.findByUserIdDetail(userId, String.valueOf(userLogin));
    }
    
    public List<ViewDocumentFile> getViewDocumentFileByProjectId(int projectId, int userId){
    	return vdfr.findByProjectIdByOrderByUploadDateAsc(userId, String.valueOf(userId), projectId);
    }
}