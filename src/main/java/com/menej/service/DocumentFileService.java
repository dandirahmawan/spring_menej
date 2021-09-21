package com.menej.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.menej.model.db.DocumentFile;
import com.menej.model.view.ViewDocumentFile;
import com.menej.repo.DocumentFileRepo;
import com.menej.repo.ViewDocumentFileRepo;

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
        df.setDescriptionFile(descFile);
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
        String path = documentFiles.get(0).getPath();
        
    	for(int i = 0; i < documentFiles.size(); i++) {
    		try {
				/*removing file*/
                File file = new File(path);
                file.delete();
			} catch (Exception e) {
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